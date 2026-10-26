package com.soyoyoo.jpa;

import com.soyoyoo.jpa.model.Article;
import com.soyoyoo.jpa.model.Member;
import com.soyoyoo.jpa.repository.ArticleRepository;
import com.soyoyoo.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class JpaApplication implements ApplicationRunner {
	private final MemberRepository memberRepository;
	private final ArticleRepository articleRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		var member1 = Member.builder().name("홍길동").email("hong@soyoyoo.com").age(10).build();
		log.info("save 홍길동");
		memberRepository.save(member1);
		log.info("saved {}", member1);
		var article = Article.builder()
				.title("방학 첫날이다")
				.description("오늘은 열심히 방학 숙제를 했다")
				.member(member1).build();
		articleRepository.save(article);

		var articles = articleRepository.findAll();
		log.info("{}", articles);

		var member2 = Member.builder().name("김길동").email("kim@soyoyoo.com").age(20).build();
		log.info("save 김길동");
		memberRepository.save(member2);
		log.info("saved {}", member2);

		log.info("read 홍길동");
		member1 = memberRepository.findById(member1.getId()).orElseThrow();

		log.info("update 홍길동");
		member1.setAge(11);
		memberRepository.save(member1);
		log.info("updated {}", member1);

		log.info("update 김길동");
		memberRepository.save(member2);
		log.info("updated {}", member2);

		log.info("애플리케이션 종료");



	}
}
