module rubikfx {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires org.fxyz3d.importers;

    opens com.jpl.games to javafx.graphics;
}
