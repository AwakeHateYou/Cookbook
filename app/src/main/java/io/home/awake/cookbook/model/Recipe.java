package io.home.awake.cookbook.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Модель рецепта.
 */
public class Recipe implements Parcelable{
    int id;
    String title, ingredients, steps;
    public Recipe(){
        this.id = 0;
    }

    /**
     * Конструктор
     * @param id
     * @param title
     * @param ingredients
     * @param steps
     */
    public Recipe(int id, String title, String ingredients, String steps){
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    /**
     * Конструктор из пакета.
     * @param in
     */
    public Recipe(Parcel in){
        id = in.readInt();
        title =  in.readString();
        ingredients = in.readString();
        steps = in.readString();
    }

    /**
     * Создание рецепта в бд.
     */
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
    public boolean isNew() {
        return id == 0;
    }

    /**
     * Запись в пакет.
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(ingredients);
        dest.writeString(steps);
    }
    @Override
    public int describeContents() {
        return 0;
    }

}
