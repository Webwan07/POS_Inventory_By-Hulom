package assets;

@Author("Josuan Leonardo Hulom")
public interface AppInitializers {
    
    default void initialize() {
        init_user();
    }
    void init_user();
}
