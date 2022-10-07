package rikkei.academy.model.song;

public class Song {
    private int id;
    private String name;
    private int listen;
    private String img;
    private String audio;

    public Song(String name, int listen, String img, String audio) {
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
    }

    public Song(int id, String name, int listen, String img, String audio) {
        this.id = id;
        this.name = name;
        this.listen = listen;
        this.img = img;
        this.audio = audio;
    }

    public Song(int id, String name, int listen) {
        this.id = id;
        this.name = name;
        this.listen = listen;

    }

    public Song(int id, String name, int listen, String img) {
        this.id = id;
        this.name = name;
        this.listen = listen;
        this.img = img;
    }

    public Song() {

    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListen() {
        return listen;
    }

    public void setListen(int listen) {
        this.listen = listen;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listen=" + listen +
                ", img='" + img + '\'' +
                '}';
    }
}
