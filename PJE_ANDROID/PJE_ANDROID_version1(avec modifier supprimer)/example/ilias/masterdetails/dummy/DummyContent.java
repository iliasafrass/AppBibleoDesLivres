package com.example.ilias.masterdetails.dummy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final  List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
   public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();




    static {
    }


    public static void addItem(DummyItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /*
    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }*/
/*
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about item : ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
*/
    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String titre;
        public final String auteur;
        public final String isbn;
        public final String nature;
        public final String genre;
        public final String editeur;
        public final String resume;
        public final String commentaire;


        public DummyItem(String id, String titre, String auteur, String isbn,String editeur,String nature,String genre,
                         String resume,String commentaire) {
            this.id = id;
            this.titre = titre;
            this.auteur = auteur;
            this.isbn = isbn;
            this.editeur = editeur;
            this.nature =nature;
            this.genre = genre;
            this.resume = resume;
            this.commentaire = commentaire;
        }

        @Override
        public String toString() {
            return titre;
        }
    }
}
