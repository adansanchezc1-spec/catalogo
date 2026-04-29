package model.main.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testConstructorAndGetters() {
        Cliente cliente = new Cliente("Cliente Test", "ID1");

        assertEquals("Cliente Test", cliente.getNombre());
        assertEquals("ID1", cliente.getId());
    }
}