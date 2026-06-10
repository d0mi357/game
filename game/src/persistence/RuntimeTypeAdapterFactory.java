package persistence;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Originalquelle: https://github.com/google/gson/blob/master/extras/src/main/java/com/google/gson/typeadapters/RuntimeTypeAdapterFactory.java
 * Lizenz: Apache License 2.0
 *
 * Diese Klasse ermöglicht die Serialisierung und Deserialisierung von polymorphen Typen mit GSON.
 * Sie speichert die Typinformationen in einem benannten Feld (z. B. "typ") und verwendet diese,
 * um beim Parsen den richtigen Subtyp zu instanziieren.
 *
 * Beispielhafte Anwendung: Serialisieren von Aufgaben und Meilenstein als gemeinsame Oberklasse.
 *
 * @param <T> die Basisklasse, von der alle registrierten Subtypen erben
 */

public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final String typeFieldName;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap<>();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap<>();

    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName) {
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }
    
    /**
     * Erstellt eine neue RuntimeTypeAdapterFactory für eine bestimmte Basisklasse.
     *
     * @param baseType       die Oberklasse aller Subtypen
     * @param typeFieldName  der Feldname, unter dem der Typ im JSON gespeichert wird
     * @param <T>            generischer Typ der Basisklasse
     * @return eine neue RuntimeTypeAdapterFactory
     */
    
    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName);
    }
    
    
    /**
     * Registriert einen Subtyp mit einem Label, das im JSON verwendet wird.
     *
     * @param type  die Subklasse, die serialisiert/deserialisiert werden soll
     * @param label eindeutiger Bezeichner für den Typ im JSON
     * @return diese Factory zur weiteren Konfiguration
     */
    
    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        labelToSubtype.put(label, type);
        subtypeToLabel.put(type, label);
        return this;
    }

    /**
     * Erstellt einen TypeAdapter, der zur Verarbeitung der registrierten Subtypen dient.
     * Diese Methode wird intern von Gson aufgerufen.
     *
     * @param gson  Gson-Instanz
     * @param type  der Typ, für den der Adapter erstellt werden soll
     * @param <R>   generischer Typ
     * @return TypeAdapter für polymorphe Typen oder null, wenn nicht zutreffend
     */
    
    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) return null;

        final Map<String, TypeAdapter<?>> labelToDelegate = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new LinkedHashMap<>();
        for (Map.Entry<String, Class<?>> entry : labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            labelToDelegate.put(entry.getKey(), delegate);
            subtypeToDelegate.put(entry.getValue(), delegate);
        }

        return new TypeAdapter<R>() {
            
            /**
             * Serialisiert das Objekt mit zusätzlichem Typfeld.
             */
        	
            @Override
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = subtypeToLabel.get(srcType);
                @SuppressWarnings("unchecked")
                TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToDelegate.get(srcType);
                JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();
                jsonObject.addProperty(typeFieldName, label);
                Streams.write(jsonObject, out);
            }
            	
            /**
             * Deserialisiert ein Objekt basierend auf dem Typfeld im JSON.
             */
            @Override
            public R read(JsonReader in) throws IOException {
                JsonElement jsonElement = Streams.parse(in);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement labelJsonElement = jsonObject.remove(typeFieldName);
                String label = labelJsonElement.getAsString();
                @SuppressWarnings("unchecked")
                TypeAdapter<R> delegate = (TypeAdapter<R>) labelToDelegate.get(label);
                return delegate.fromJsonTree(jsonObject);
            }
        };
    }
}
