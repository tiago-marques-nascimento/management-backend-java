package com.users.management.component;

import java.util.List;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;
import com.users.management.infrastructure.response.ControllerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.users.management.application.viewmodel.UserViewModel;

public abstract class GenericComponentTest<T> {

	private String token;

    protected abstract int getPort();
    protected abstract TestRestTemplate getRestTemplate();
    protected abstract T parseJson(String json) throws Exception;
    protected abstract List<T> parseJsonList(String json) throws Exception;

    protected void login() throws Exception {

        UserViewModel user = new UserViewModel();
        user.setName("admin");
        user.setPassword("admin");

		ObjectMapper mapper = new ObjectMapper();

		HttpEntity<UserViewModel> entity = new HttpEntity<UserViewModel>(user);
		this.token = mapper.readValue(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + "/login", HttpMethod.POST, entity, String.class).getBody(),
			new TypeReference<ControllerResponse<String>>(){}).getData();
    }

    protected String get(String endpoint) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);
        return this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.GET, entity, String.class).getBody();
    }

    protected String post(String endpoint, T body) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity<T> entity = new HttpEntity<T>(body, headers);
        return this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.POST, entity, String.class).getBody();
    }

    protected String put(String endpoint, T body) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity<T> entity = new HttpEntity<T>(body, headers);
        return this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.PUT, entity, String.class).getBody();
    }

    protected String delete(String endpoint) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);
        return this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.DELETE, entity, String.class).getBody();
    }

    protected T getObject(String endpoint) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);
        ObjectMapper mapper = new ObjectMapper();
        return this.parseJson(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.GET, entity, String.class).getBody());
    }

    protected List<T> getObjectList(String endpoint) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);
        ObjectMapper mapper = new ObjectMapper();
        return this.parseJsonList(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.GET, entity, String.class).getBody());
    }

    protected T postObject(String endpoint, T body) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity<T> entity = new HttpEntity<T>(body, headers);
        ObjectMapper mapper = new ObjectMapper();
        return this.parseJson(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.POST, entity, String.class).getBody());
    }

    protected T putObject(String endpoint, T body) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity<T> entity = new HttpEntity<T>(body, headers);
        ObjectMapper mapper = new ObjectMapper();
        return this.parseJson(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.PUT, entity, String.class).getBody());
    }

    protected T deleteObject(String endpoint) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);
        ObjectMapper mapper = new ObjectMapper();
        return this.parseJson(this.getRestTemplate().exchange("http://localhost:" + this.getPort() + endpoint, HttpMethod.DELETE, entity, String.class).getBody());
    }
}
