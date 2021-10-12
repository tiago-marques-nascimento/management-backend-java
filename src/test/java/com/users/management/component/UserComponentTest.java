package com.users.management.component;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.infrastructure.response.ControllerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.users.management.application.factory.UserViewModelFactory;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserComponentTest extends GenericComponentTest<UserViewModel> {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Override
	protected int getPort() {
		return this.port;
	}

	@Override
	protected TestRestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	@Override
	protected UserViewModel parseJson(String json) throws Exception {
		return this.mapper.readValue(json, new TypeReference<ControllerResponse<UserViewModel>>(){}).getData();
	}

	@Override
	protected List<UserViewModel> parseJsonList(String json) throws Exception {
		return this.mapper.readValue(json, new TypeReference<ControllerResponse<List<UserViewModel>>>(){}).getData();
	}

    @BeforeEach
    public void setUp() throws Exception {
		this.login();
    }

	@Test
	public void authenticateAndFindAllUsersTest() throws Exception {

		String response = this.get("/user");

		assertThat(response).contains("\"name\":\"admin\"");
	}

	@Test
	public void authenticateAndFindByNameNoUserFoundTest() throws Exception {

		String response = this.get("/user/Diogo");

        assertThat(response).isEqualTo("{\"data\":null}");
	}

	@Test
	public void authenticateAndCreateUserAndFindUserTest() throws Exception {

		UserViewModel user = UserViewModelFactory.mockUser();
		this.post("/user", user);
		String response = this.get("/user/" + user.getName());

        assertThat(response).contains("\"name\":\"" + user.getName() + "\"");
		assertThat(response).contains("\"name\":\"" + user.getRoles().get(0).getName() + "\"");
	}

	@Test
	public void authenticateAndUpdateUserTest() throws Exception {

		UserViewModel user = UserViewModelFactory.mockUpdateUser();
		this.post("/user", user);
		user = this.getObject("/user/" + user.getName());
		user.setName("Updated_" + user.getName());
		this.put("/user", user);
		String response = this.get("/user/" + user.getName());

        assertThat(response).contains("\"name\":\"" + user.getName() + "\"");
		assertThat(response).contains("\"name\":\"admin\"");
	}

	@Test
	public void authenticateAndDeleteUserTest() throws Exception {

		UserViewModel user = UserViewModelFactory.mockDeleteUser();
		this.post("/user", user);
		user = this.getObject("/user/" + user.getName());
		this.delete("/user/" + user.getId());
		String response = this.get("/user/" + user.getName());

        assertThat(response).isEqualTo("{\"data\":null}");
	}
}
