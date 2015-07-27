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
 * Тест - редактирование персоны
 */
public class PersonEditServletTest extends Mockito {

    final PersonCache cache = PersonCache.getInstance();

    @Test
    public void personEdit() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nameOfPerson")).thenReturn("renamed");
        when(request.getParameter("nameOfPet")).thenReturn("renamed");

        assertTrue(cache.values().isEmpty());

        cache.add(new Person(1, "test", new Cat("test")));

        new PersonEditServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("nameOfPerson");
        verify(request, atLeast(1)).getParameter("nameOfPet");

        assertFalse(cache.values().isEmpty());

        cache.delete(cache.findByName("renamed").getId());
    }
}