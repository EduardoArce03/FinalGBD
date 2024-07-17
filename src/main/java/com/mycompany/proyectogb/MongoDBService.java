/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectogb;

import Model.Cancion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBService {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBService(String connectionString, String dbName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(dbName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
    
    public void insertarCancion(Cancion cancion) {
        MongoCollection<Document> collection = database.getCollection("music");
        collection.insertOne(cancion.toDocument());
    }
    
     public void buscarCanciones() {
        MongoCollection<Document> collection = database.getCollection("music");
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }
}