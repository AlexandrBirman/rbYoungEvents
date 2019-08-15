package parse.scrupers;

public class ScruperEvent {
    private String date;
    private String url;
    private String name;
    private String location;
    private String price;
    private String category;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ScruperEvent(String url) {
        this.url = url;
    }

    public ScruperEvent() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getUrl());

        if (getName() != null)
            builder.append("\n" + getName());

        if (getDate() != null)
            builder.append("\n" + getDate());

        if (getLocation() != null)
            builder.append("\n" + getLocation());

        if (getPrice() != null)
            builder.append("\n" + getPrice());

        if (getCategory() != null)
            builder.append("\n" + getCategory());

        return builder.toString();
    }
}
