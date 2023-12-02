package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	RestTemplate restTemplate = new RestTemplate();

	private final String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random";

	@Override
	public CatPic getPic() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject(API_BASE_URL, CatPic.class);
	}

}	
