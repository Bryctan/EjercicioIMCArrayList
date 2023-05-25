import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {
	ArrayList<String> listaNombres;
	ArrayList<Long> listaTelefonos;
	ArrayList<String> listaEnfermedades = new ArrayList<String>();
	ArrayList<Integer> listaPosiciones = new ArrayList<Integer>();

	public Procesos() {
		listaNombres = new ArrayList<String>();
		listaTelefonos = new ArrayList<Long>();
		iniciar();
	}

	public void iniciar() {
		validarMenu();
	}

	private void validarMenu() {
		String menu = "";
		menu += "-----Bienvenido al menu de calculo de IMC-----\n";
		menu += "1. Ingresar Personas \n";
		menu += "2. Mostrar por nombre \n";
		menu += "3. Mostrar Lista Completa \n";
		menu += "4. Actualizar persona \n";
		menu += "5. Eliminar persona \n";
		menu += "6. Salir \n";

		int opcionMenu = 0;
		do {
			opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));

			switch (opcionMenu) {
			case 1:
				ingresarPersonas();
				break;
			case 2:
				if (listaNombres.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					imprimirPorNombre();
				}
				break;
			case 3:
				if (listaNombres.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					imprimirLista();
				}
				break;
			case 4:
				if (listaNombres.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					actualizarPersona();
				}
				break;
			case 5:
				if (listaNombres.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					eliminarDatos ();
				}
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "¡Gracias por usar el programa!");
				break;

			default:
				JOptionPane.showMessageDialog(null, "¡Opción invalida!");
				break;
			}

		} while (opcionMenu != 6);

	}

	private void ingresarPersonas() {
		long telefono = 0;
		double peso = 0, talla = 0, imc = 0;
		String preg = "";
		do {
			String nombre = "";
			nombre = JOptionPane.showInputDialog("Ingrese su nombre ");
			telefono = Long.parseLong(JOptionPane.showInputDialog("Ingrese su telefono"));
			peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su peso"));
			talla = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su talla"));
			listaNombres.add(nombre.toLowerCase());
			listaTelefonos.add(telefono);
			imc = peso / (talla * talla);
			validarImc(imc);
			preg = JOptionPane.showInputDialog("¿Desea ingresar mas personas?");

		} while (preg.equalsIgnoreCase("si"));
	}

	private void validarImc(double imc) {
		String estadoSalud = "";
		if (imc < 18) {
			estadoSalud = "Anorexia";

		} else if (imc >= 18 && imc < 20) {
			estadoSalud = "Delgadez";
		} else if (imc >= 20 && imc < 27) {
			estadoSalud = "Normalidad";
		} else if (imc >= 27 && imc < 30) {
			estadoSalud = "Obecidad (Grado 1)";
		} else if (imc >= 30 && imc < 35) {
			estadoSalud = "Obecidad (Grado 2)";
		} else if (imc >= 35 && imc < 40) {
			estadoSalud = "Obecidad (Grado 3";
		} else if (imc >= 40) {
			estadoSalud = "Obecidad morbida";
		}
		listaEnfermedades.add(estadoSalud);
	}

	private void imprimirLista() {
		for (int i = 0; i < listaNombres.size(); i++) {
			System.out.println("Persona = " + listaNombres.get(i) + "\n" + "Telefono = " + listaTelefonos.get(i) + "\n"
					+ "Resultado = " + listaEnfermedades.get(i));
		}
	}

	private void imprimirPorNombre() {

		String pregNombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a buscar").toLowerCase();
		int cantidad = 0;
		if (listaNombres.contains(pregNombre)) {

			for (int i = 0; i < listaNombres.size(); i++) {

				if (listaNombres.get(i).equalsIgnoreCase(pregNombre)) {
					cantidad++;
					JOptionPane.showMessageDialog(null, "La persona " + pregNombre + " tiene un estado de salud de "
							+ listaEnfermedades.get(i) + " En la posicion #" + i);
				}
			}
			JOptionPane.showMessageDialog(null,
					"La persona " + pregNombre + " se encontro " + cantidad + " vez/veces");

		} else {
			JOptionPane.showMessageDialog(null, "La persona " + pregNombre + " no se encuentra registrada");
		}
	}

	private void actualizarPersona () {
		
		String menuActu = "¿Que desea actualizar? \n";
		menuActu += "1. Actualizar el nombre \n";
		menuActu += "2. Actualizar el telefono \n";
		menuActu += "3. Actualizar el nombre y el telefono \n";
		menuActu += "4 Salir \n";
		
		int opcion = Integer.parseInt(JOptionPane.showInputDialog(menuActu));
		String mensaje = "",nombreNuevo = "",nombreAct = "",pregunta ="";
		int posicion=0;
		long telefono=0;
		
		switch (opcion) {
		case 1:
			
		do {			
			nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar").toLowerCase();
			
			if (listaNombres.contains(nombreAct)) {
				mensaje += "Se encontro la persona"+nombreAct+"en las siguientes posiciones; "+ "\n";
				for (int i = 0; i < listaNombres.size(); i++) {
					if (listaNombres.get(i).equalsIgnoreCase(nombreAct)) {
						mensaje += "Posicion #"+i+" "+nombreAct+ " \n";
						listaPosiciones.add(i);
					}
				}
				mensaje += "Ingrese la posicion que desea actualizar";
				
				do {
					posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
					if (listaPosiciones.contains(posicion)) {
						nombreNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre").toLowerCase();
						listaNombres.remove(posicion);
						listaNombres.add(posicion,nombreNuevo);
						break;
					}
					else {
						JOptionPane.showMessageDialog(null, "Opcion Invalida");
					}
					
				} while (true);
				
				mensaje = "";
				break;
			} else {
				
				JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
			}
		} 
		while (!listaNombres.contains(nombreAct));
			
		pregunta = JOptionPane.showInputDialog("¿Desea actualizar otra persona?");
		
		if (pregunta.equalsIgnoreCase("si")) {
			actualizarPersona ();
		}
		
		break;
		
		case 2:
			
			do {
				nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar el telefono").toLowerCase();
				
				if (listaNombres.contains(nombreAct)) {
					mensaje += "Se encontro la persona "+nombreAct+" en las siguientes posiciones: "+ "\n";
					for (int i = 0; i < listaNombres.size(); i++) {
						if (listaNombres.get(i).equalsIgnoreCase(nombreAct)) {
							mensaje += "Posicion #"+i+" "+nombreAct+"con telefono: "+listaTelefonos.get(i) +" \n";
							listaPosiciones.add(i);
						}
					}
					mensaje += "Ingrese la posicion que desea actualizar";
					
					do {
						posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
						if (listaPosiciones.contains(posicion)) {
							telefono = Long.parseLong(JOptionPane.showInputDialog("Ingrese el nuevo telefono"));
							listaTelefonos.remove(posicion);
							listaTelefonos.add(posicion,telefono);
							break;
						}
						else {
							JOptionPane.showMessageDialog(null, "Opcion Invalida");
						}
						
					} while (true);
					
					mensaje = "";
					break;
				} else {
					
					JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
				}
			} while (!listaNombres.contains(nombreAct));
				
			pregunta = JOptionPane.showInputDialog("¿Desea actualizar el telefono de otra persona?");
			
			if (pregunta.equalsIgnoreCase("si")) {
				actualizarPersona ();
			}
			
		break;

		case 3:
			do {
				nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar el nombre y el telefono").toLowerCase();
				
				if (listaNombres.contains(nombreAct)) {
					mensaje += "Se encontro la persona "+nombreAct+" en las siguientes posiciones: "+ "\n";
					for (int i = 0; i < listaNombres.size(); i++) {
						if (listaNombres.get(i).equalsIgnoreCase(nombreAct)) {
							mensaje += "Posicion #"+i+" "+nombreAct+" con telefono: "+listaTelefonos.get(i) +" \n";
							listaPosiciones.add(i);
						}
					}
					mensaje += "Ingrese la posicion que desea actualizar";
					
					do {
						posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
						if (listaPosiciones.contains(posicion)) {
							nombreNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre").toLowerCase();
							telefono = Long.parseLong(JOptionPane.showInputDialog("Ingrese el nuevo telefono"));
							listaNombres.remove(posicion);
							listaNombres.add(posicion,nombreNuevo);
							listaTelefonos.remove(posicion);
							listaTelefonos.add(posicion,telefono);
							break;
						}
						else {
							JOptionPane.showMessageDialog(null, "Opcion Invalida");
						}
						
					} while (true);
					
					mensaje = "";
					break;
				} else {
					
					JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
				}
			} while (!listaNombres.contains(nombreAct));
				
			pregunta = JOptionPane.showInputDialog("¿Desea actualizar el nombre y el telefono de otra persona?");
			
			if (pregunta.equalsIgnoreCase("si")) {
				actualizarPersona ();
			}
			
			break;
			
		case 4:
			break;
			
		default:
			JOptionPane.showMessageDialog(null, "Opcion Invalida");
			break;
		}	
	}
	
	
	
	private void eliminarDatos () {
		String mensaje ="",pregunta ="",nombreEli="";
		int posicion=0;
		
		do {
			nombreEli = JOptionPane.showInputDialog("Ingrese del nombre de la persona que quiere eliminar").toLowerCase();
			if (listaNombres.contains(nombreEli)) {
				mensaje += "Se encontro la persona "+nombreEli+" en las siguientes posiciones; "+ "\n";
				for (int i = 0; i < listaNombres.size(); i++) {
					if (listaNombres.get(i).equalsIgnoreCase(nombreEli)) {
						mensaje += "Posicion #"+i+" "+nombreEli+" "+listaTelefonos.get(i)+" "+listaEnfermedades.get(i)+" \n";
						listaPosiciones.add(i);
					}
					
				}
				mensaje += "Ingrese la posicion que desea eliminar";
				
				do {
					posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
					if (listaPosiciones.contains(posicion)) {
						listaNombres.remove(posicion);
						break;
					}
					else {
						JOptionPane.showMessageDialog(null, "Opcion Invalida");
					}
					
				} while (true);
				
				mensaje = "";
				break;
			} else {
				
				JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreEli);
			}
		} 
		while (!listaNombres.contains(nombreEli));
			
		pregunta = JOptionPane.showInputDialog("¿Desea eliminar otra persona?");
		
		if (pregunta.equalsIgnoreCase("si")) {
			eliminarDatos ();
		}
		
	}
	
	
}
