package FB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.Scanner;

class Controleur implements ActionListener {
	public static int i;

	CModele modele;

	public Controleur(CModele modele) {
		this.modele = modele;
	}

	// Enclenche l'inondation et la fin de tour
	public void actionPerformed(ActionEvent e) {
		modele.flood3Zones();
		this.modele.getJeu().finDeTour();
		CVue.updateArea(this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].toString());
	}

}

// classes qui implementent ce qu'on fait lorsqu'on clique un bouton, appelle la
// classe controlleur
// qui a son tour appelle la classe joueur (ou game?) pour effectuer l'action
// necessaire

class UpListener implements ActionListener {
	CModele modele;

	public UpListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Up
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			if (z.getY() > 0) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.move(this.modele.getZone(z.getX(), z.getY() - 1));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Impossible d'aller en haut choisissez une nouvelle direction");
			}
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class DownListener implements ActionListener {
	CModele modele;

	public DownListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Down
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			if (z.getY() < this.modele.getIle().height()) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.move(this.modele.getZone(z.getX(), z.getY() + 1));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Impossible d'aller en bas choisissez une nouvelle direction");
			}
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class LeftListener implements ActionListener {
	CModele modele;

	public LeftListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Left
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			if (z.getX() > 0) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.move(this.modele.getZone(z.getX() - 1, z.getY()));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Impossible d'aller a gauche choisissez une nouvelle direction");
			}
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class RightListener implements ActionListener {
	CModele modele;

	public RightListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Right
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			if (z.getX() < this.modele.getIle().getWidth()) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.move(this.modele.getZone(z.getX() + 1, z.getY()));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Impossible d'aller a droite choisissez une nouvelle direction");
			}
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class DryListener implements ActionListener {
	CModele modele;

	public DryListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Dry
	public void actionPerformed(ActionEvent e) {
		up up = new up(modele);
		down down = new down(modele);
		right right = new right(modele);
		left left = new left(modele);
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			Object source = e.getSource();

			if (source == this) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.dryUp(this.modele.getJeu().getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone());
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
				System.out.println("DRYY");
			} else if (e.getSource() == up) {
				up.actionPerformed(e);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(this.modele.getJeu().getIle()
						.getZone(this.modele.getJeu().getIle().id(z.getX(), z.getY() - 1)));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else if (e.getSource() == down) {
				down.actionPerformed(e);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(this.modele.getJeu().getIle()
						.getZone(this.modele.getJeu().getIle().id(z.getX(), z.getY() + 1)));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else if (e.getSource() == right) {
				right.actionPerformed(e);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(this.modele.getJeu().getIle()
						.getZone(this.modele.getJeu().getIle().id(z.getX() + 1, z.getY())));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else if (e.getSource() == left) {
				left.actionPerformed(e);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(this.modele.getJeu().getIle()
						.getZone(this.modele.getJeu().getIle().id(z.getX() - 1, z.getY())));
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			}
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}

}

class ArtifactListener implements ActionListener {
	CModele modele;

	public ArtifactListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Get Artifact
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		Artifact[] art = this.modele.getJeu().getArtifacts();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			int avant = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfArtifact();
			for (Artifact a : art) {
				if (a.getZone() == this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone()) {
					this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].recupereArtefact(a);
				}
			}
			int apres = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfArtifact();
			if (avant == apres) {
				CVue.updateArea("Dommage, pas d'artefact!");
			} else {
				CVue.updateArea("Bravo, vous avez trouvez un artefact!!");
			}
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class HereListener implements ActionListener {
	CModele modele;

	public HereListener(CModele modele) {
		this.modele = modele;
	}

	// Changement a effectuer lorsqu'on clique le bouton Up
	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
					.dryUp(this.modele.getJeu().getIle().getZone(this.modele.getJeu().getIle().id(z.getX(), z.getY())));
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class up implements ActionListener {
	CModele modele;

	public up(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(
					this.modele.getJeu().getIle().getZone(this.modele.getJeu().getIle().id(z.getX(), z.getY() - 1)));
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class down implements ActionListener {
	CModele modele;

	public down(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(
					this.modele.getJeu().getIle().getZone(this.modele.getJeu().getIle().id(z.getX(), z.getY() + 1)));
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class left implements ActionListener {
	CModele modele;

	public left(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(
					this.modele.getJeu().getIle().getZone(this.modele.getJeu().getIle().id(z.getX() - 1, z.getY())));
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class right implements ActionListener {
	CModele modele;

	public right(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Zone z = this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getZone();
		if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].dryUp(
					this.modele.getJeu().getIle().getZone(this.modele.getJeu().getIle().id(z.getX() + 1, z.getY())));
			this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
			CVue.updateArea("Il vous reste "
					+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
					+ " actions");
		} else {
			CVue.updateArea("Vous n'avez plus d'action disponible !!!");
		}
	}
}

class gg implements ActionListener {
	CModele modele;

	public gg(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().isWon()) {
			CVue.updateArea("GG VOUS AVEZ GAGNE");
		} else {
			CVue.updateArea("La partie n'est pas finie !");
		}
		this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
	}
}

class UN implements ActionListener {
	CModele modele;

	public UN(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getCurrentPlayer() == 0) {
			CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
		} else {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.getZone() != this.modele.getJeu().getJoueurs()[0].getZone()) {
					CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
				} else {
					Controleur.i = 0;
				}
			

			} else {
				CVue.updateArea("Vous n'avez plus d'action disponible !!!");
			}
		}
	}
}

class DEUX implements ActionListener {
	CModele modele;

	public DEUX(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getCurrentPlayer() == 1) {
			CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
		} else {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.getZone() != this.modele.getJeu().getJoueurs()[1].getZone()) {
					CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
				} else {
					Controleur.i = 1;
				}
		
			}

			else {
				CVue.updateArea("Vous n'avez plus d'action disponible !!!");
			}
		}
	}
}

class TROIS implements ActionListener {
	CModele modele;

	public TROIS(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getCurrentPlayer() == 2) {
			CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
		} else {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.getZone() != this.modele.getJeu().getJoueurs()[2].getZone()) {
					CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
				} else {
					Controleur.i = 2;

				}

			} else {
				CVue.updateArea("Vous n'avez plus d'action disponible !!!");
			}
		}
	}
}

class QUATRE implements ActionListener {
	CModele modele;

	public QUATRE(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getCurrentPlayer() == 3) {
			CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
		} else {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.getZone() != this.modele.getJeu().getJoueurs()[3].getZone()) {
					CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
				} else {
					Controleur.i = 3;
				}
	

			} else {
				CVue.updateArea("Vous n'avez plus d'action disponible !!!");
			}
		}
	}
}

class CINQ implements ActionListener {
	CModele modele;

	public CINQ(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getJoueurs().length < 5) {
			CVue.updateArea("Pas de joueur dispo");
		} else {
			if (this.modele.getJeu().getCurrentPlayer() == 5) {
				CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
			} else {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
					if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
							.getZone() != this.modele.getJeu().getJoueurs()[4].getZone()) {
						CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
					} else {
						Controleur.i = 4;
					}

				} else {
					CVue.updateArea("Vous n'avez plus d'action disponible !!!");
				}
			}
		}
	}
}

class SIX implements ActionListener {
	CModele modele;

	public SIX(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.modele.getJeu().getJoueurs().length < 6) {
			CVue.updateArea("Pas de joueur dispo");
		} else {
			if (this.modele.getJeu().getCurrentPlayer() == 6) {
				CVue.updateArea("Vous ne pouvez pas vous auto echanger une cle !!");
			} else {
				if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions() > 0) {
					if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
							.getZone() != this.modele.getJeu().getJoueurs()[5].getZone()) {
						CVue.updateArea("Vous ne pouvez donner de cle a un joueur eloigne !");
					} else {
						Controleur.i = 5;
					}
					/*
					 * CVue.
					 * updateArea("Entrez un entier : 0 pour Air, 1 pour Eau, 2 pour Feu, 3 pour Terre :"
					 * ); if(e.getSource() == AIR) { Keys K = new Keys(Elements.Air);
					 * if(this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().
					 * contains(K)) {
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].echangerKey
					 * (this.modele.getJoueurs()[5], K);
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * actionPerformed(); CVue.updateArea("Il vous reste "+
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * getNumberOfActions()+ " actions"); } else {
					 * CVue.updateArea("filler"); } } else
					 * if(e.getSource() == EAU) { Keys K = new Keys(Elements.Eau);
					 * if(this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().
					 * contains(K)) {
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].echangerKey
					 * (this.modele.getJoueurs()[5], K);
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * actionPerformed(); CVue.updateArea("Il vous reste "+
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * getNumberOfActions()+ " actions"); } else {
					 * CVue.updateArea("filler"); } } else
					 * if(e.getSource() == FEU) { Keys K = new Keys(Elements.Feu);
					 * if(this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().
					 * contains(K)) {
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].echangerKey
					 * (this.modele.getJoueurs()[5], K);
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * actionPerformed(); CVue.updateArea("Il vous reste "+
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * getNumberOfActions()+ " actions"); } else {
					 * CVue.updateArea("filler"); } } else
					 * if(e.getSource() == TERRE) { Keys K = new Keys(Elements.Terre);
					 * if(this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().
					 * contains(K)) {
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].echangerKey
					 * (this.modele.getJoueurs()[5], K);
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * actionPerformed(); CVue.updateArea("Il vous reste "+
					 * this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].
					 * getNumberOfActions()+ " actions"); } else {
					 * CVue.updateArea("filler"); } } else {
					 * CVue.updateArea("filler");
					 * } }
					 */

				} else {
					CVue.updateArea("Vous n'avez plus d'action disponible !!!");
				}
			}
		}
	}
}

class AIR implements ActionListener {
	CModele modele;

	public AIR(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Keys k = new Keys(Elements.Air);
		if (Controleur.i == 0) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[0], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element AIR");
			}
		} else if (Controleur.i == 1) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[1], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element AIR");
			}
		} else if (Controleur.i == 2) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[2], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element AIR");
			}
		} else if (Controleur.i == 3) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[3], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element AIR");
			}
		} else if (Controleur.i == 4) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[4], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de lelement AIR");
			}
		} else if (Controleur.i == 5) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[5], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element AIR");
			}
		}
		Controleur.i = -1;
	}
}

class EAU implements ActionListener {
	CModele modele;

	public EAU(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Keys k = new Keys(Elements.Eau);
		if (Controleur.i == 0) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[0], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		} else if (Controleur.i == 1) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[1], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		} else if (Controleur.i == 2) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[2], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		} else if (Controleur.i == 3) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[3], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		} else if (Controleur.i == 4) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[4], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		} else if (Controleur.i == 5) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[5], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element EAU");
			}
		}
		Controleur.i = -1;
	}
}

class FEU implements ActionListener {
	CModele modele;

	public FEU(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Keys k = new Keys(Elements.Feu);
		if (Controleur.i == 0) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[0], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		} else if (Controleur.i == 1) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[1], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		} else if (Controleur.i == 2) {

			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[2], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		} else if (Controleur.i == 3) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[3], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		} else if (Controleur.i == 4) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[4], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		} else if (Controleur.i == 5) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[5], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element FEU");
			}
		}
		Controleur.i = -1;
	}
}

class TERRE implements ActionListener {
	CModele modele;

	public TERRE(CModele modele) {
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Keys k = new Keys(Elements.Terre);
		if (Controleur.i == 0) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[0], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		} else if (Controleur.i == 1) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[1], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		} else if (Controleur.i == 2) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[2], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		} else if (Controleur.i == 3) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[3], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		} else if (Controleur.i == 4) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[4], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		} else if (Controleur.i == 5) {
			if (this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].keys().contains(k)) {
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()]
						.echangerKey(this.modele.getJoueurs()[5], k);
				this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].actionPerformed();
				CVue.updateArea("Il vous reste "
						+ this.modele.getJoueurs()[this.modele.getJeu().getCurrentPlayer()].getNumberOfActions()
						+ " actions");
			} else {
				CVue.updateArea("Vous n'avez pas de cle de l'element TERRE");
			}
		}
		Controleur.i = -1;
	}
}

