package kz.petsclinic.servlets;

import kz.petsclinic.store.PersonCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест - добавление персоны
 */
public class PersonCreateServletTest extends Mockito {

    final PersonCache cache = PersonCache.getInstance();

    @Test
    public void createPerson() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("nameOfPerson")).thenReturn("test");
        when(request.getParameter("nameOfPet")).thenReturn("test");
        when(request.getParameter("classOfPet")).thenReturn("Cat");

        assertTrue(cache.values().isEmpty());

        new PersonCreateServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("nameOfPerson");
        verify(request, atLeast(1)).getParameter("nameOfPet");
        verify(request, atLeast(1)).getParameter("classOfPet");
        assertFalse(cache.values().isEmpty());

        cache.delete(cache.findByName("test").getId());
    }
}