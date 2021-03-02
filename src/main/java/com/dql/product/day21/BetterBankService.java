package com.dql.product.day21;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class BetterBankService {


    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        createUserAPI.setMobile(mobile);
        return remoteCall(createUserAPI);
    }

    public static String pay(long userId, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setUserId(userId);
        payAPI.setAmount(amount);
        return remoteCall(payAPI);
    }

    private static String remoteCall(AbstractAPI api) throws IOException {
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        StringBuilder builder = new StringBuilder();
        Arrays.stream(api.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(BankAPIField.class))
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order()))
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    switch (bankAPIField.type()) {
                        case "S": {
                            builder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            builder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M":{
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            builder.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
        //签名逻辑
        builder.append(DigestUtils.md2Hex(builder.toString()));
        String param = builder.toString();
        long begin = System.currentTimeMillis();
        //发请求
        String result = Request.Post("http://localhost:8088/reflection" + bankAPI.url())
                .bodyString(param, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return result;

    }



}
