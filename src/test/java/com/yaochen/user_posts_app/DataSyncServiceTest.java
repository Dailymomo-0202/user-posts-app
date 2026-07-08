package com.yaochen.user_posts_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.yaochen.user_posts_app.DTO.UserApiResponse;
import com.yaochen.user_posts_app.Repository.PostRepository;
import com.yaochen.user_posts_app.Repository.UserRepository;
import com.yaochen.user_posts_app.Service.DataSyncService;


@ExtendWith(MockitoExtension.class)
class DataSyncServiceTest {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PostRepository postRepository;
	@InjectMocks
	private DataSyncService dataSyncService;


	@Test
	void testSyncData() {
		UserApiResponse mockUser = new UserApiResponse();
		mockUser.setId(1L);
		mockUser.setName("Test User 1");
		UserApiResponse.Company company = new UserApiResponse.Company();
		company.setName("Test Company");
		mockUser.setCompany(company);

		UserApiResponse.Address address = new UserApiResponse.Address();
		address.setStreet("Test Street");
		address.setSuite("Test Suite");
		address.setCity("Test City");
		address.setZipcode("Test Zipcode");
		mockUser.setAddress(address);

		UserApiResponse[] mockResponse = new UserApiResponse[] { mockUser };
		when(restTemplate.getForObject(anyString(), eq(UserApiResponse[].class)))
				.thenReturn(mockResponse);

		//Act
		dataSyncService.syncData();

		//Assert
		verify(userRepository, times(1)).save(any());
		
	
	}

}
