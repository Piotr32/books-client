package app;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@RestController
public class BookData {

    @GetMapping("/getBookData")
    public void getBookData() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Book[]> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/books",
                HttpMethod.GET,
                null,
                Book[].class);

        Stream.of(responseEntity.getBody()).forEach(System.out::println);
    }

    @GetMapping("/deleteBookData")
    public void deleteBookData() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/books?id=1",
                HttpMethod.DELETE,
                null,
                Boolean.class);
        System.out.println(responseEntity.getBody());
    }

    @GetMapping("/addBookData")
    public void addBookData() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        String objToSent = "{\n" +
                "        \"id\": 5,\n" +
                "        \"title\": \"HTML,CSS,JavaScript\",\n" +
                "        \"author\": \"Laura Lemay, Rafe Colburn, Jennifer Kyrnin,\",\n" +
                "        \"pages\": 702\n" +
                "    }";
        HttpEntity httpEntity = new HttpEntity(objToSent, httpHeaders);

        restTemplate.exchange(
                "http://localhost:8080/api/books",
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }
}
