package assets;

@Author("Josuan Leonardo Hulom")
public interface AppInitializers {
    
    default void initialize() {
        init_user();
        init_table();
        init_models();
    }
    void init_user();
    void init_table();
    void init_models();
}
