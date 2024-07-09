package com.hyf.test.learn;

import com.hyf.hotrefresh.common.NamedThreadFactory;
import com.hyf.hotrefresh.common.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class BookDownload {

    @Bean
    public RestTemplate bookDownloadRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(BookDownload.class, args);
    }

    @Component
    public static class Initializer implements CommandLineRunner {

        public static final String HOME_PATH = "C:\\Users\\baB_hyf\\Desktop\\book\\";

        @Autowired
        @Qualifier("bookDownloadRestTemplate")
        private RestTemplate restTemplate;

        static {
            new File(HOME_PATH).mkdirs();
        }

        @Override
        public void run(String... args) throws Exception {

            Map<String, String> map = new HashMap<>();

            map.put("数小一上", "https://v2.ykt.cbern.com.cn/65/document/ddf39e4fff4f4414bb8c280d118cf289/pdf.pdf");
            map.put("数小一下", "https://v1.ykt.cbern.com.cn/65/document/6dee326ca03f48e69177e46d6994eb53/pdf.pdf");
            map.put("数小二上", "https://v2.ykt.cbern.com.cn/65/document/9cdf8c482ae84f8297ac36124439725b/pdf.pdf");
            map.put("数小二下", "https://v1.ykt.cbern.com.cn/65/document/6ec20b98073742c4978140e62a3c2fc2/pdf.pdf");
            map.put("数小三上", "https://v1.ykt.cbern.com.cn/65/document/ba5dfa373a654a86971c79dba0a4de74/pdf.pdf");
            map.put("数小三下", "https://v3.ykt.cbern.com.cn/65/document/fd8e4c03259d449e8401be87404ded10/pdf.pdf");
            map.put("数小四上", "https://v1.ykt.cbern.com.cn/65/document/39bde729c0444e6f89dce6dcdff19535/pdf.pdf");
            map.put("数小四下", "https://v3.ykt.cbern.com.cn/65/document/9d0945362c494b438c908c5324201e91/pdf.pdf");
            map.put("数小五上", "https://v3.ykt.cbern.com.cn/65/document/d05b059d523d418883c61aae555c3b33/pdf.pdf");
            map.put("数小五下", "https://v2.ykt.cbern.com.cn/65/document/55c1a821b02647f1856490c4decdfa9b/pdf.pdf");
            map.put("数小六上", "https://v2.ykt.cbern.com.cn/65/document/ac93d71297f94c99bb88ce7940b4d4dc/pdf.pdf");
            map.put("数小六下", "https://v2.ykt.cbern.com.cn/65/document/10f49134b83a4304aa2e3fc139b459a6/pdf.pdf");
            map.put("数初一上", "https://v1.ykt.cbern.com.cn/65/document/cdc8c107bd7e44f49ad6cd6c6e453d0c/pdf.pdf");
            map.put("数初一下", "https://v1.ykt.cbern.com.cn/65/document/dfd728827e474926b75ff28a2e39554d/pdf.pdf");
            map.put("数初二上", "https://v3.ykt.cbern.com.cn/65/document/dc677f1f6d8d4116b0ddad3461d1c358/pdf.pdf");
            map.put("数初二下", "https://v3.ykt.cbern.com.cn/65/document/b5d94c1403a7460e92ecede483de9763/pdf.pdf");
            map.put("数初三上", "https://v3.ykt.cbern.com.cn/65/document/76be52ced73a4aeab7593d6e85b227c3/pdf.pdf");
            map.put("数初三下", "https://v2.ykt.cbern.com.cn/65/document/cf5ca5e0d2434a1bab1523a4f6537ea7/pdf.pdf");
            map.put("数高必一", "https://v3.ykt.cbern.com.cn/65/document/ce8c01029f184841bf81526e8b5c312d/pdf.pdf");
            map.put("数高必二", "https://v1.ykt.cbern.com.cn/65/document/336581e97b6941ab8fb6211fcacf63bd/pdf.pdf");
            map.put("数高选一", "https://v1.ykt.cbern.com.cn/65/document/ae0cb713d524457b973e7c79b8ebe8ae/pdf.pdf");
            map.put("数高选二", "https://v1.ykt.cbern.com.cn/65/document/bb1282f8c46540cc9701c23096ec8c6c/pdf.pdf");

            map.put("政小一上", "https://v3.ykt.cbern.com.cn/65/document/a99f662acf204783844599c56f2bfb1d/pdf.pdf");
            map.put("政小一下", "https://v1.ykt.cbern.com.cn/65/document/db0c1b9855fe47d6b7726f01fdbca068/pdf.pdf");
            map.put("政小二上", "https://v3.ykt.cbern.com.cn/65/document/d6fe17b01e464dd8bba8b712ceb31b77/pdf.pdf");
            map.put("政小二下", "https://v2.ykt.cbern.com.cn/65/document/82e4b95a9fe74890b73b984d175a7dc4/pdf.pdf");
            map.put("政小三上", "https://v1.ykt.cbern.com.cn/65/document/602f40a455df4c11b790f6799cd2e1a8/pdf.pdf");
            map.put("政小三下", "https://v3.ykt.cbern.com.cn/65/document/51b1dd797e3f4d57887d0e188a233f3c/pdf.pdf");
            map.put("政小四上", "https://v1.ykt.cbern.com.cn/65/document/a32329b8b357446ea4b8adef305d5bf1/pdf.pdf");
            map.put("政小四下", "https://v2.ykt.cbern.com.cn/65/document/a6f5dad74d104fbb9c558a56f7d6ff22/pdf.pdf");
            map.put("政小五上", "https://v1.ykt.cbern.com.cn/65/document/7f3fd5f23dc14b90864ee5e01924e0ab/pdf.pdf");
            map.put("政小五下", "https://v2.ykt.cbern.com.cn/65/document/5c19f465af7744e6baf25f2df340e477/pdf.pdf");
            map.put("政小六上", "https://v2.ykt.cbern.com.cn/65/document/4b82d169751a4d3abac53ef3a5a272a9/pdf.pdf");
            map.put("政小六下", "https://v1.ykt.cbern.com.cn/65/document/c93a52f6a3e54e0ea6ba390f15de7427/pdf.pdf");
            map.put("政小读一", "https://v2.ykt.cbern.com.cn/65/document/d3d40d94a1bf11ec9c6bfa20200f090a/pdf.pdf");
            map.put("政小读二", "https://v3.ykt.cbern.com.cn/65/document/d3d40d6fa1bf11ec9c6bfa20200f090a/pdf.pdf");
            map.put("政初一上", "https://v1.ykt.cbern.com.cn/65/document/7fec16bca0df41759572fe6cf51d3b62/pdf.pdf");
            map.put("政初一下", "https://v1.ykt.cbern.com.cn/65/document/63ebb5d35d134a7baddc5d0ba643f214/pdf.pdf");
            map.put("政初二上", "https://v2.ykt.cbern.com.cn/65/document/39064a8d17224287ba7d60108ec6a3d4/pdf.pdf");
            map.put("政初二下", "https://v3.ykt.cbern.com.cn/65/document/3684b79fa049416886d0ce9e927fad70/pdf.pdf");
            map.put("政初三上", "https://v2.ykt.cbern.com.cn/65/document/b547557b761248bb860ba60730332039/pdf.pdf");
            map.put("政初三下", "https://v1.ykt.cbern.com.cn/65/document/c82b1e20e1964f2480ab15d1d43ab797/pdf.pdf");
            map.put("政初读一", "https://v1.ykt.cbern.com.cn/65/document/d3d40d4ea1bf11ec9c6bfa20200f090a/pdf.pdf");
            map.put("政高必一", "https://v2.ykt.cbern.com.cn/65/document/9369c9451c1e480b99312e5c84698819/pdf.pdf");
            map.put("政高必二", "https://v3.ykt.cbern.com.cn/65/document/69c792cd27b54af893ba4e3d127cf85a/pdf.pdf");
            map.put("政高必三", "https://v2.ykt.cbern.com.cn/65/document/23d32e153d504a68ad30a7053feca2f7/pdf.pdf");
            map.put("政高必四", "https://v3.ykt.cbern.com.cn/65/document/391c84ca32b24121a3ba6d1a53e22259/pdf.pdf");
            map.put("政高选一", "https://v2.ykt.cbern.com.cn/65/document/b7b29290e078467885da6b234dd1a69a/pdf.pdf");
            map.put("政高选二", "https://v3.ykt.cbern.com.cn/65/document/3cfc4cae13dc45d7bb4053bbc169d661/pdf.pdf");
            map.put("政高选三", "https://v3.ykt.cbern.com.cn/65/document/d0e2605aefcf48c9a6aa901f63290105/pdf.pdf");
            map.put("政高读一", "https://v2.ykt.cbern.com.cn/65/document/d3d40db6a1bf11ec9c6bfa20200f090a/pdf.pdf");

            map.put("历初一上", "https://v1.ykt.cbern.com.cn/65/document/7318543a2d4542408cdc3d8fa48b7381/pdf.pdf");
            map.put("历初一下", "https://v2.ykt.cbern.com.cn/65/document/778dcfbddbeb446db5ef3564e3eeca55/pdf.pdf");
            map.put("历初二上", "https://v2.ykt.cbern.com.cn/65/document/219b32a2ceac4effbc5635a2c699f0a9/pdf.pdf");
            map.put("历初二下", "https://v3.ykt.cbern.com.cn/65/document/1b411e4b708d4ab38956079f830df4f4/pdf.pdf");
            map.put("历初三上", "https://v1.ykt.cbern.com.cn/65/document/5a310d03a59346c3a631fe7ac70f66e5/pdf.pdf");
            map.put("历初三下", "https://v1.ykt.cbern.com.cn/65/document/48763beb0beb453a81111b4703b04788/pdf.pdf");
            map.put("历高必一", "https://v2.ykt.cbern.com.cn/65/document/e6ce3a276ed6463da8302fb8336b0799/pdf.pdf");
            map.put("历高必二", "https://v1.ykt.cbern.com.cn/65/document/74bf5f3720b042a580c1135b5b38d376/pdf.pdf");
            map.put("历高选一", "https://v3.ykt.cbern.com.cn/65/document/7b4f452b9fd5485fa47f5a525cf92f14/pdf.pdf");
            map.put("历高选二", "https://v3.ykt.cbern.com.cn/65/document/cc3a18d392144e67b0f422431aba88e5/pdf.pdf");
            map.put("历高选三", "https://v2.ykt.cbern.com.cn/65/document/59abc3724e004023b19c5dc9bdc704bd/pdf.pdf");

            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30, 30, 1, TimeUnit.DAYS,
                    new LinkedBlockingQueue<>(), new NamedThreadFactory("hyf_download", 999, false));

            AtomicInteger i = new AtomicInteger(map.size());

            map.forEach((name, url) -> {
                threadPoolExecutor.execute(() -> {
                    try {

                        File file = new File(HOME_PATH + "" + name + ".pdf");
                        if (file.exists()) {
                            return;
                        }

                        ResponseEntity<Resource> entity = restTemplate.getForEntity(url, Resource.class);
                        Resource resource = entity.getBody();
                        if (resource == null) {
                            throw new RuntimeException("resource is null, url: " + url);
                        }

                        try (InputStream is = resource.getInputStream();
                             FileOutputStream fos = new FileOutputStream(file)) {
                            IOUtils.writeTo(is, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        System.out.println(i.decrementAndGet());
                    }
                });
            });

            System.out.println();
        }
    }
}