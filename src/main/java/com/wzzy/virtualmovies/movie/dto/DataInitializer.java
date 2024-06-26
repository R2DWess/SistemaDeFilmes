package com.wzzy.virtualmovies.movie.dto;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("systemMoviesUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            MovieRepository movieRepository = new MovieRepository(em);
            MoviesService moviesService = new MoviesService(em);

            initializeMovies(moviesService);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Initialization failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void initializeMovies(MoviesService moviesService) {
        List<Movie> movies = Arrays.asList(
                new Movie("Avatar", 2009, 162, Arrays.asList("Ação", "Aventura", "Fantasia"), "James Cameron", Arrays.asList("James Cameron"), Arrays.asList("Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Stephen Lang"), "https://xl.movieposterdb.com/09_08/2009/499549/xl_499549_cd69e2ea.jpg", 83, "Sci-Fi", "https://avatar.example.com"),
                new Movie("Eu Sou a Lenda", 2007, 101, Arrays.asList("Drama", "Ficção Científica", "Terror"), "Francis Lawrence", Arrays.asList("Akiva Goldsman", "Mark Protosevich", "Richard Matheson"), Arrays.asList("Will Smith", "Alice Braga", "Charlie Tahan"), "https://xl.movieposterdb.com/07_12/2007/480249/xl_480249_66680d6b.jpg", 65, "Drama", "https://example.com/eu-sou-a-lenda"),
                new Movie("300", 2006, 117, Arrays.asList("Ação", "Drama"), "Zack Snyder", Arrays.asList("Zack Snyder", "Kurt Johnstad", "Michael Gordon", "Frank Miller", "Lynn Varley"), Arrays.asList("Gerard Butler", "Lena Headey", "David Wenham"), "https://xl.movieposterdb.com/07_09/2006/0416449/xl_0416449_ebba784f.jpg", 52, "Ação", "https://example.com/300"),
                new Movie("O Lobo de Wall Street", 2013, 180, Arrays.asList("Biografia", "Comédia", "Crime"), "Martin Scorsese", Arrays.asList("Terence Winter", "Jordan Belfort"), Arrays.asList("Leonardo DiCaprio", "Jonah Hill", "Margot Robbie"), "https://xl.movieposterdb.com/13_11/2013/993846/xl_993846_6b55e2ea.jpg", 75, "Comédia", "https://example.com/o-lobo-de-wall-street"),
                new Movie("Interestelar", 2014, 169, Arrays.asList("Aventura", "Drama", "Ficção Científica"), "Christopher Nolan", Arrays.asList("Jonathan Nolan", "Christopher Nolan"), Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), "https://xl.movieposterdb.com/14_09/2014/816692/xl_816692_6fbec03a.jpg", 74, "Sci-Fi", "https://example.com/interestelar"),
                new Movie("À Procura da Felicidade", 2006, 117, Arrays.asList("Biografia", "Drama"), "Gabriele Muccino", Arrays.asList("Steve Conrad"), Arrays.asList("Will Smith", "Thandiwe Newton", "Jaden Smith"), "https://xl.movieposterdb.com/07_12/2006/454921/xl_454921_9d11428d.jpg", 6, "Drama", "https://example.com/a-procura-da-felicidade"),
                new Movie("Antes do Amanhecer", 1995, 101, Arrays.asList("Drama", "Romance"), "Richard Linklater", Arrays.asList("Richard Linklater", "Kim Krizan"), Arrays.asList("Ethan Hawke", "Julie Delpy"), "https://xl.movieposterdb.com/23_02/1995/112471/xl_before-sunrise-movie-poster_1fb3d59d.jpg", 77, "Romance", "https://example.com/antes-do-amanhecer"),
                new Movie("Questão de Tempo", 2013, 123, Arrays.asList("Comédia", "Drama", "Fantasia"), "Richard Curtis", Arrays.asList("Richard Curtis"), Arrays.asList("Domhnall Gleeson", "Rachel McAdams", "Bill Nighy"), "https://xl.movieposterdb.com/13_06/2013/2194499/xl_2194499_c0435606.jpg", 68, "Drama", "https://example.com/questao-de-tempo"),
                new Movie("Orgulho e Preconceito", 2005, 129, Arrays.asList("Drama", "Romance"), "Joe Wright", Arrays.asList("Deborah Moggach", "Jane Austen"), Arrays.asList("Keira Knightley", "Matthew Macfadyen", "Brenda Blethyn"), "https://m.media-amazon.com/images/S/pv-target-images/e5a295dcc5441e9a1fd5907e191578d510ed7f8be46c5e85a5dd51354bfd3cb6.jpg", 82, "Romance", "https://example.com/orgulho-e-preconceito"),
                new Movie("Matrix", 1999, 136, Arrays.asList("Ação", "Ficção Científica"), "Lana Wachowski, Lilly Wachowski", Arrays.asList("Lilly Wachowski", "Lana Wachowski"), Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"), "https://xl.movieposterdb.com/06_11/1999/0133093/xl_145384_0133093_fd241228.jpg", 87, "Sci-Fi", "https://example.com/matrix"),
                new Movie("A Viagem de Chihiro", 2001, 125, Arrays.asList("Animação", "Aventura", "Família"), "Hayao Miyazaki", Arrays.asList("Hayao Miyazaki"), Arrays.asList("Rumi Hiiragi", "Miyu Irino", "Mari Natsuki"), "https://xl.movieposterdb.com/14_05/2001/245429/xl_245429_233c79aa.jpg", 96, "Família", "https://example.com/a-viagem-de-chihiro"),
                new Movie("Carros", 2006, 117, Arrays.asList("Animação", "Aventura", "Comédia"), "John Lasseter, Joe Ranft", Arrays.asList("John Lasseter", "Joe Ranft", "Jorgen Klubien"), Arrays.asList("Owen Wilson", "Bonnie Hunt", "Paul Newman"), "https://xl.movieposterdb.com/15_12/2006/317219/xl_317219_18797820.jpg", 73, "Animação", "https://example.com/carros"),
                new Movie("Parasita", 2019, 132, Arrays.asList("Comédia", "Drama", "Thriller"), "Bong Joon-ho", Arrays.asList("Bong Joon-ho", "Han Jin-won"), Arrays.asList("Kang-ho Song", "Sun-kyun Lee", "Yeo-jeong Cho"), "https://xl.movieposterdb.com/21_11/2019/6751668/xl_6751668_0d0409c5.jpg", 96, "Thriller", "https://example.com/parasita"),
                new Movie("A Chegada", 2016, 116, Arrays.asList("Drama", "Mistério", "Ficção Científica"), "Denis Villeneuve", Arrays.asList("Eric Heisserer", "Ted Chiang"), Arrays.asList("Amy Adams", "Jeremy Renner", "Forest Whitaker"), "https://xl.movieposterdb.com/22_10/2016/2543164/xl_arrival-movie-poster_a18b5408.jpg", 81, "Sci-Fi", "https://example.com/a-chegada"),
                new Movie("Filhos da Esperança", 2006, 109, Arrays.asList("Ação", "Drama", "Ficção Científica"), "Alfonso Cuarón", Arrays.asList("Alfonso Cuarón", "Timothy J. Sexton", "David Arata", "Mark Fergus", "Hawk Ostby"), Arrays.asList("Clive Owen", "Julianne Moore", "Chiwetel Ejiofor"), "https://xl.movieposterdb.com/13_03/2006/206634/xl_206634_af29190c.jpg", 84, "Sci-Fi", "https://example.com/filhos-da-esperanca"),
                new Movie("Blade Runner 2049", 2017, 164, Arrays.asList("Ação", "Drama", "Mistério"), "Denis Villeneuve", Arrays.asList("Hampton Fancher", "Michael Green", "Philip K. Dick"), Arrays.asList("Harrison Ford", "Ryan Gosling", "Ana de Armas"), "https://xl.movieposterdb.com/23_02/2018/8151756/xl_blade-runner-2049-designing-the-world-of-blade-runner-2049-movie-poster_078b7954.jpg", 81, "Sci-Fi", "https://example.com/blade-runner-2049"),
                new Movie("Ela", 2013, 126, Arrays.asList("Drama", "Romance", "Ficção Científica"), "Spike Jonze", Arrays.asList("Spike Jonze"), Arrays.asList("Joaquin Phoenix", "Amy Adams", "Scarlett Johansson"), "https://xl.movieposterdb.com/13_11/2013/1798709/xl_1798709_48f9f547.jpg", 91, "Romance", "https://example.com/ela"),
                new Movie("Embriagados de Amor", 2002, 95, Arrays.asList("Comédia", "Drama", "Romance"), "Paul Thomas Anderson", Arrays.asList("Paul Thomas Anderson"), Arrays.asList("Adam Sandler", "Emily Watson", "Philip Seymour Hoffman"), "https://xl.movieposterdb.com/22_01/2002/272338/xl_272338_bb20bcda.jpg", 78, "Romance", "https://example.com/embriagados-de-amor"),
                new Movie("Borat", 2006, 84, Arrays.asList("Comédia"), "Larry Charles", Arrays.asList("Sacha Baron Cohen", "Anthony Hines", "Peter Baynham", "Dan Mazer", "Todd Phillips"), Arrays.asList("Sacha Baron Cohen", "Ken Davitian", "Luenell"), "https://xl.movieposterdb.com/07_10/2006/443453/xl_443453_7ea199e5.jpg", 89, "Comédia", "https://example.com/borat"),
                new Movie("50/50", 2011, 100, Arrays.asList("Comédia", "Drama", "Romance"), "Jonathan Levine", Arrays.asList("Will Reiser"), Arrays.asList("Joseph Gordon-Levitt", "Seth Rogen", "Anna Kendrick"), "https://xl.movieposterdb.com/21_02/2011/1306980/xl_1306980_1ac06c4e.jpg", 72, "Drama", "https://example.com/50-50"),
                new Movie("Duelo de Titãs", 2000, 113, Arrays.asList("Biografia", "Drama", "Esporte"), "Boaz Yakin", Arrays.asList("Gregory Allen Howard"), Arrays.asList("Denzel Washington", "Will Patton", "Wood Harris"), "https://xl.movieposterdb.com/08_09/2000/210945/xl_210945_d59653b1.jpg", 48, "Esporte", "https://example.com/duelo-de-titas"),
                new Movie("Onde os Fracos Não Têm Vez", 2007, 122, Arrays.asList("Crime", "Drama", "Thriller"), "Ethan Coen, Joel Coen", Arrays.asList("Joel Coen", "Ethan Coen", "Cormac McCarthy"), Arrays.asList("Tommy Lee Jones", "Javier Bardem", "Josh Brolin"), "https://3.bp.blogspot.com/-VOSxfvZLWIs/UbIicnqQZZI/AAAAAAAACaA/VVjjy882ybc/s1600/capa+onde+os+fracos+(converted).jpg", 74, "Thriller", "https://example.com/onde-os-fracos-nao-tem-vez")
        );

        for (Movie movie : movies) {
            moviesService.save(movie);
        }

        System.out.println("Movies initialized successfully.");
    }
}
