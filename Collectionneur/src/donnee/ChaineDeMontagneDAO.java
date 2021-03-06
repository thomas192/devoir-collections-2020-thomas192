package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modele.ChaineDeMontagne;


public class ChaineDeMontagneDAO {

	public List<ChaineDeMontagne> listerChainesDeMontagnes() {
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<ChaineDeMontagne> listeChainesDeMontagnes =  new ArrayList<ChaineDeMontagne>();			
		Statement requeteListeChainesDeMontagnes;
		
		try {
			requeteListeChainesDeMontagnes = connection.createStatement();
			ResultSet curseurListeChainesDeMontagnes = requeteListeChainesDeMontagnes.executeQuery("SELECT * from chainedemontagne");
			while (curseurListeChainesDeMontagnes.next()) {
				
				int id = curseurListeChainesDeMontagnes.getInt("id");
				String nom = curseurListeChainesDeMontagnes.getString("nom");
				
				ChaineDeMontagne collection = new ChaineDeMontagne();
				collection.setId(id);
				
				collection.setNom(nom);
				listeChainesDeMontagnes.add(collection);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return listeChainesDeMontagnes;
	}

	public ChaineDeMontagne detaillerChaineDeMontagne(int id) {
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		ChaineDeMontagne chaineDeMontagne =  new ChaineDeMontagne();			
		PreparedStatement requeteChaineDeMontagne;
			try {
				requeteChaineDeMontagne = connection.prepareStatement("SELECT * from chainedemontagne WHERE id = ?");
				requeteChaineDeMontagne.setInt(1, id);
				
				ResultSet curseur = requeteChaineDeMontagne.executeQuery();
				curseur.next();
				id = curseur.getInt("id");
				String nom = curseur.getString("nom");
				String description = curseur.getString("description");
				chaineDeMontagne.setId(id);
				chaineDeMontagne.setNom(nom);
				chaineDeMontagne.setDescription(description);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return chaineDeMontagne;
	}
	
}
