import de.projekt.carlook.dao.ReservationDAO;
import de.projekt.carlook.dao.entity.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ReservationRoundTripTest {

    private ReservationDAO reservationDAO;

    @Before
    public void setUp()  {
        reservationDAO = new ReservationDAO();
    }


    @Test
    public void roundTripTest() {
        String userMail = "toni@bmw.de";
        int carId = 3;

        Reservation reservation = new Reservation(carId, userMail);

        // Reservierung erzeugen
        boolean isCreated = reservationDAO.create(reservation);

        // Reservierung lesen
        List<Reservation> reservations = reservationDAO.readAllByUser(userMail);
        int sizeAfterInsert = reservations.size();
        Assert.assertTrue(sizeAfterInsert > 0);

        Reservation created = null;
        for(Reservation reservation1 : reservations){
            if(reservation1.getCar_id() == carId && reservation1.getEmail().equals(userMail)){
                created = reservation1;
                break;
            }
        }

        Assert.assertNotNull(created);

        // Reservierung löschen
        boolean isDeleted = reservationDAO.delete(created.getId());

        reservations = reservationDAO.readAllByUser(userMail);
        Assert.assertEquals(reservations.size(), sizeAfterInsert - 1);



    }
}
