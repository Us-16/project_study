package com.example.app;

import com.example.app.aes.AES256;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.NumberFormat;

@Slf4j
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		String cipherText;
		AES256 aes256 = new AES256();
		String text = "!! Hello world !!";
		try {
			cipherText = aes256.encrypt(text);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		System.out.println("Text: " + text);

		System.out.println("Cipher Text: " + cipherText);
		try {
			System.out.println("Decode: "+ aes256.decrypt(cipherText));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
