package org.geotools.tutorial.quickstart;


import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.api.data.FeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.filter.Filter;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PolyShpConverter {


    public static void main(String[] args) throws Exception {

        // please enter your shp file path below
        File file = new File("C:/Users/.....");


        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        Filter filter = Filter.INCLUDE;

        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);

        try (FeatureIterator<SimpleFeature> features = collection.features()) {

            SimpleFeature a = features.next();
            Geometry yeni = (Geometry) a.getDefaultGeometry();
            System.out.println(Arrays.toString(yeni.getCoordinates()));

            Coordinate[] coords = yeni.getCoordinates();

            // please enter your shp file path above
            try (PrintWriter writer = new PrintWriter(new FileWriter("C:/Users/....."))) {

                // x and y are coming from JTS library
                for (Coordinate coordinate : coords) {
                    writer.println(coordinate.x + ";" + coordinate.y);
                }
                System.out.println("CSV file has been created succesfully!");
            }

        }


    }


}