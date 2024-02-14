package org.main.dao;

import org.main.Flight;
import org.main.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CollectionFlightsDAO implements FlightsDAO {
    List<Flight> flights;
    private final File f;

    public CollectionFlightsDAO(File f) {
        this.flights = new ArrayList<Flight>();
        this.f = f;
    }

    //    private void saveDataToFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
//            oos.writeObject(database);
//            FamilyLogger.info("Запис сім'ї");
//        } catch (IOException e) {
//            FamilyLogger.error("Помилка запису сім'ї");
//            e.printStackTrace();
//        }
//    }
//
//    private void loadDataFromFile() {
//        if (f.exists()) {
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
//                database = (List<Family>) ois.readObject();
//                FamilyLogger.info("Читання файлу");
//            } catch (IOException | ClassNotFoundException e) {
//                FamilyLogger.error("Помилка читання файлу");
//                e.printStackTrace();
//            }
//        }
//    }

}
