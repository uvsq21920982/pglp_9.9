package fr.uvsq.pglp.Command;
import fr.uvsq.pglp.Connection.GetConnection;
import fr.uvsq.pglp.DaoJbc.*;
import fr.uvsq.pglp.FormeGraphique.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteCommand implements Command  {

    /**
     * Liste de formes a supprimer.
     */
    private ArrayList<Forme> form;
    /**
     * Constructeur.
     * @param f La liste de formes à supprimer
     */
    public DeleteCommand(final ArrayList<Forme> f) {
        this.form = f;
    }
    /**
     * Execute la commande de suppression.
     */
    public void execute() {
        Connection c = GetConnection.createBase();
        FactoryDaoJDBC fdj = new FactoryDaoJDBC(c);
        for (Forme f : form) {
            if (f.getClass() == Cercle.class) {
                CercleDaoJDBC cercle = (CercleDaoJDBC)
                        fdj.getCercleDao();
                cercle.delete((Cercle) f);
            } else if (f.getClass() == Square.class) {
                SquareDaoJDBC carre = (SquareDaoJDBC) fdj.getSquareDao();
                carre.delete((Square) f);
            } else if (f.getClass() == Rectangle.class) {
                RectangleDaoJDBC rectangle = (RectangleDaoJDBC)
                        fdj.getRectangleDao();
                rectangle.delete((Rectangle) f);
            } else if (f.getClass() == Triangle.class) {
                TriangleDaoJDBC triangle = (TriangleDaoJDBC)
                        fdj.getTriangleDao();
                triangle.delete((Triangle) f);
            } else if (f.getClass() == Groupe.class) {
                GroupeDaoJDBC groupe = (GroupeDaoJDBC)
                        fdj.getGroupeDao();
                groupe.delete((Groupe) f);
            }
        }
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

