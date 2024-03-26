package co.com.apirest.project.utils;

public enum Uri {
    LIST_USERS("/api/users?page=2"),
    CREATE_USER("/api/users"),
    UPDATE_USER("/api/users/2"),
    DELETE("/api/users/2");

    private String uri;

    Uri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}