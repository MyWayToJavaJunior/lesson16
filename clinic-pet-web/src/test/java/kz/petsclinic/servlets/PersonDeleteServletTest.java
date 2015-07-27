package kz.petsclinic.servlets;

import kz.petsclinic.models.Cat;
import kz.petsclinic.models.Person;
import kz.petsclinic.store.PersonCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Тест - удаление персоны
 */
public class PersonDeleteServletTest extends Mockito {

    final PersonCache cache = PersonCache.getInstance();

    @Test
    public void deletePerson() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        assertTrue(cache.values().isEmpty());
        cache.add(new Person(1, "test", new Cat("test")));
        assertFalse(cache.values().isEmpty());
        when(request.getParameter("id")).thenReturn("1");
        new PersonDeleteServlet().doGet(request, response);
        assertTrue(cache.values().isEmpty());
    }
}