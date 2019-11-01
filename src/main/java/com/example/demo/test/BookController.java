package com.example.demo.test;



import com.alibaba.fastjson.JSONObject;
import com.example.demo.vo.VUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
public class BookController {

    //调用接口
     @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test99")
    public void test1(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
//添加请求的参数
        params.add("hello", "hello");             //必传
        params.add("world", "world");           //选传
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/test99", HttpMethod.POST, requestEntity, String.class);  //最后的参数需要用String.class  使用其他的会报错
        String body = response.getBody();
        System.out.println("哈喽"+body);
     /*   if(body != null){
            JSONObject data = (JSONObject) JSON.parse(body);
            //data就是返回的结果
        }else{

        }*/

    }

    @PostMapping("/test88")
    public String test2(@RequestBody  String  user){
        System.out.println(user);
       return user;
    }
}
