package com.example.liquibase.service.impl;

import com.example.liquibase.dto.AuthorsResponseDto;
import com.example.liquibase.dto.BooksDto;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties ")
class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("FIND ALL AUTHOR")
    @Timeout(value = 2,unit = SECONDS)
    void findAll() {
        List<BooksDto> booksList = new ArrayList<>();
        booksList.add(new BooksDto(1L,"Dziady",1832,"https://ecsmedia.pl/c/dziady-w-iext39528243.jpg","Wydanie Dziadów kompletne bez skrótów i cięć w treści. W tym wydaniu znajdziesz odpowiedzi na pytania z podręcznika - pewniak na teście, czyli wskazanie zagadnień, które zwykle pojawiają się w pytaniach z danej lektury we wszelkich testach sprawdzających wiedzę, a także w podręcznikach i na klasówkach. Zawiera bardzo szczegółowe streszczenie oraz drugie skrócone, ułatwiające szybkie przygotowanie się przed lekcją. Opracowanie zawiera plan wydarzeń, wnikliwie wyjaśnioną problematykę oraz szerokie charakterystyki bohaterów.",false));
        booksList.add(new BooksDto(2L,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true));
        List<AuthorsResponseDto> author = new ArrayList<>();
        author.add(new AuthorsResponseDto(1,"Adam","Mickiewicz",booksList));
        assertThat(authorService.findAll())
                .usingRecursiveComparison()
                .isEqualTo(author);
    }

    @Test
    @DisplayName("FIND ID AUTHOR")
    void findById() throws NotFoundException {
        List<BooksDto> booksList = new ArrayList<>();
        booksList.add(new BooksDto(1L,"Dziady",1832,"https://ecsmedia.pl/c/dziady-w-iext39528243.jpg","Wydanie Dziadów kompletne bez skrótów i cięć w treści. W tym wydaniu znajdziesz odpowiedzi na pytania z podręcznika - pewniak na teście, czyli wskazanie zagadnień, które zwykle pojawiają się w pytaniach z danej lektury we wszelkich testach sprawdzających wiedzę, a także w podręcznikach i na klasówkach. Zawiera bardzo szczegółowe streszczenie oraz drugie skrócone, ułatwiające szybkie przygotowanie się przed lekcją. Opracowanie zawiera plan wydarzeń, wnikliwie wyjaśnioną problematykę oraz szerokie charakterystyki bohaterów.",false));
        booksList.add(new BooksDto(2L,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true));
        AuthorsResponseDto author = new AuthorsResponseDto(1,"Adam","Mickiewicz",booksList);
        assertThat(authorService.findById(1L))
                .usingRecursiveComparison()
                .isEqualTo(author);

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            authorService.findById(1000L);
        });
    }

    @Test
    @DisplayName("DELETE ID")
    void deleteById() throws NotFoundException {
        authorService.deleteById(1L);
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            authorService.findById(1L);
        });
    }

    @Test
    @DisplayName("SAVE AUTHOR")
    void save() {
        List<BooksDto> booksList = new ArrayList<>();
        booksList.add(new BooksDto(1L,"Dziady",1832,"https://ecsmedia.pl/c/dziady-w-iext39528243.jpg","Wydanie Dziadów kompletne bez skrótów i cięć w treści. W tym wydaniu znajdziesz odpowiedzi na pytania z podręcznika - pewniak na teście, czyli wskazanie zagadnień, które zwykle pojawiają się w pytaniach z danej lektury we wszelkich testach sprawdzających wiedzę, a także w podręcznikach i na klasówkach. Zawiera bardzo szczegółowe streszczenie oraz drugie skrócone, ułatwiające szybkie przygotowanie się przed lekcją. Opracowanie zawiera plan wydarzeń, wnikliwie wyjaśnioną problematykę oraz szerokie charakterystyki bohaterów.",false));
        booksList.add(new BooksDto(2L,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true));
        AuthorsResponseDto author = new AuthorsResponseDto(1,"Adam","Mickiewicz",booksList);
        List<AuthorsResponseDto> authorsResponseDtoArrayList = new ArrayList<>();
        authorsResponseDtoArrayList.add(new AuthorsResponseDto(1,"Adam","Mickiewicz",booksList));
        authorService.save(author);
        assertThat(authorService.findAll())
                .usingRecursiveComparison()
                .isEqualTo(authorsResponseDtoArrayList);
    }

}
