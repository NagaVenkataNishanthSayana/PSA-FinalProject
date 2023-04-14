package org.TSP.Graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VertexTest {
    @Test
    public void testConstructorAndGetters() {
        Vertex vertex = new Vertex("London", 51.5074, -0.1278);
        assertEquals("London", vertex.getId());
        assertEquals(51.5074, vertex.getLongitude(), 0.0001);
        assertEquals(-0.1278, vertex.getLatitude(), 0.0001);
        assertEquals(0, vertex.getDegree());
    }

    @Test
    public void testSetters() {
        Vertex vertex = new Vertex();
        vertex.setId("B");
        vertex.setLongitude(4.0);
        vertex.setLatitude(5.0);
        vertex.setDegree(2);
        assertEquals("B", vertex.getId());
        assertEquals(4.0, vertex.getLongitude(), 0.0001);
        assertEquals(5.0, vertex.getLatitude(), 0.0001);
        assertEquals(2, vertex.getDegree());
    }

    @Test
    public void testIncrementDegree() {
        Vertex vertex = new Vertex();
        assertEquals(0, vertex.getDegree());
        vertex.incrementDegree();
        assertEquals(1, vertex.getDegree());
        vertex.incrementDegree();
        assertEquals(2, vertex.getDegree());
    }

    @Test
    public void testGettersAndSetters() {
        Vertex vertex = new Vertex();
        vertex.setId("C");
        vertex.setLatitude(6.0);
        vertex.setLongitude(7.0);
        vertex.setDegree(3);
        assertEquals("C", vertex.getId());
        assertEquals(6.0, vertex.getLatitude(), 0.0001);
        assertEquals(7.0, vertex.getLongitude(), 0.0001);
        assertEquals(3, vertex.getDegree());
    }

    @Test
    public void testSetDegree() {
        Vertex vertex = new Vertex("D", 8.0, 9.0);
        vertex.setDegree(4);
        assertEquals(4, vertex.getDegree());
    }
}
