package com.produtos.apirest.config;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	private ProdutoRepository prodRepo;

	@Autowired
	public TestConfig(ProdutoRepository prodRepo) {
		this.prodRepo = prodRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		Produto p1 = new Produto(null,"mouse",new BigDecimal("1.0"),new BigDecimal("250.0"));
		Produto p2 = new Produto(null,"mouse",new BigDecimal("1.0"),new BigDecimal("250.0"));
		Produto p3 = new Produto(null,"mouse",new BigDecimal("1.0"),new BigDecimal("250.0"));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));

	}

}
