package com.example.liquibase.service.impl;

import com.example.liquibase.dto.AuthorsDto;
import com.example.liquibase.dto.AuthorsResponseDto;
import com.example.liquibase.dto.BooksDto;
import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.model.Books;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties ")
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    @DisplayName("FIND ALL")
    void findAll() {
        List<AuthorsDto> author = new ArrayList<>();
        author.add(new AuthorsDto(1L,"Adam","Mickiewicz"));
        List<BooksResponseDto> booksList = new ArrayList<>();
        booksList.add(new BooksResponseDto(1,"Dziady",1832,"https://ecsmedia.pl/c/dziady-w-iext39528243.jpg","Wydanie Dziadów kompletne bez skrótów i cięć w treści. W tym wydaniu znajdziesz odpowiedzi na pytania z podręcznika - pewniak na teście, czyli wskazanie zagadnień, które zwykle pojawiają się w pytaniach z danej lektury we wszelkich testach sprawdzających wiedzę, a także w podręcznikach i na klasówkach. Zawiera bardzo szczegółowe streszczenie oraz drugie skrócone, ułatwiające szybkie przygotowanie się przed lekcją. Opracowanie zawiera plan wydarzeń, wnikliwie wyjaśnioną problematykę oraz szerokie charakterystyki bohaterów.",false,author));
        booksList.add(new BooksResponseDto(2,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));
        booksList.add(new BooksResponseDto(3,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));

        assertThat(bookService.findAll())
                .usingRecursiveComparison()
                .isEqualTo(booksList);
    }

    @Test
    @DisplayName("FIND ALL PAGINATION")
    void findAllPagination() {
        List<AuthorsDto> author = new ArrayList<>();
        author.add(new AuthorsDto(1L,"Adam","Mickiewicz"));
        List<BooksResponseDto> booksList = new ArrayList<>();
        booksList.add(new BooksResponseDto(1,"Dziady",1832,"https://ecsmedia.pl/c/dziady-w-iext39528243.jpg","Wydanie Dziadów kompletne bez skrótów i cięć w treści. W tym wydaniu znajdziesz odpowiedzi na pytania z podręcznika - pewniak na teście, czyli wskazanie zagadnień, które zwykle pojawiają się w pytaniach z danej lektury we wszelkich testach sprawdzających wiedzę, a także w podręcznikach i na klasówkach. Zawiera bardzo szczegółowe streszczenie oraz drugie skrócone, ułatwiające szybkie przygotowanie się przed lekcją. Opracowanie zawiera plan wydarzeń, wnikliwie wyjaśnioną problematykę oraz szerokie charakterystyki bohaterów.",false,author));
        booksList.add(new BooksResponseDto(2,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));
        booksList.add(new BooksResponseDto(3,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));

        assertThat(bookService.findAllPagination(0L,5))
                .usingRecursiveComparison()
                .isEqualTo(booksList);
    }

    @Test
    @DisplayName("FIND ID")
    void findById() throws NotFoundException {
        List<AuthorsDto> author = new ArrayList<>();
        author.add(new AuthorsDto(1L,"Adam","Mickiewicz"));
        BooksResponseDto books = new BooksResponseDto(2,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author);
        assertThat(bookService.findById(2L))
                .usingRecursiveComparison()
                .isEqualTo(books);

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            bookService.findById(1000L);
        });
    }

    @Test
    @DisplayName("DELETE ID")
    void deleteById() throws NotFoundException {
        bookService.deleteById(1L);
        List<AuthorsDto> author = new ArrayList<>();
        author.add(new AuthorsDto(1L,"Adam","Mickiewicz"));
        List<BooksResponseDto> booksList = new ArrayList<>();
        booksList.add(new BooksResponseDto(2,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));
        booksList.add(new BooksResponseDto(3,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));

        assertThat(bookService.findAll())
                .usingRecursiveComparison()
                .isEqualTo(booksList);

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            bookService.findById(1000L);
        });
    }

    @Test
    @DisplayName("ADD BOOK")
    void addBook() throws NotFoundException {
        bookService.addBook(1L,new BooksDto(null,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true));

        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> {
            bookService.addBook(1000L,new BooksDto(null,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true));

        });

        List<AuthorsDto> author = new ArrayList<>();
        author.add(new AuthorsDto(1L,"Adam","Mickiewicz"));

        assertThat(bookService.findById(3L))
                .usingRecursiveComparison()
                .isEqualTo(new BooksResponseDto(3,"Pan Tadeusz",1834,"https://www.greg.pl/imgs/covers/380/Pan-Tadeusz_lkpt.jpg","Historia szlachecka z okresu poprzedzającego wyprawę Napoleona na Moskwę. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle przełomowych dla narodu wydarzeń. Stał się on epopeją narodową, gdyż wyrażał najważniejsze treści w chwili, gdy narodowi było to niezbędne oraz krzepił serca.",true,author));

    }

    @Test
    @DisplayName("COUNT")
    void count() {
        assertTrue(bookService.count() == 3);
    }
}
