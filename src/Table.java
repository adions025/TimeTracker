/**
 *This class was already implemented and given by the project handout. 
 *It creates tables that will be used by the class PaginaWeb to create 
 *HTML content.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Table extends Elements {

  /*
  * This function is part of the visitor pattern, it's the accept part.
  */
  public Table accept(Format format) {
    return this;
  }

  private int nfiles;

  public int getNfiles() {
    return nfiles;
  }

  protected void setNfiles(int nfiles) {
    this.nfiles = nfiles;
  }

  private int ncolumnes;

  public int getNcolumnes() {
    return ncolumnes;
  }

  protected void setNcolumnes(int ncolumnes) {
    this.ncolumnes = ncolumnes;
  }

  private ArrayList taula = null;

  public ArrayList getTaula() {
    return taula;
  }

  public void setTaula(ArrayList taula) {
    this.taula = taula;
  }

  /**
   * Constructor of this object.
   */
  @SuppressWarnings("unchecked")
  public Table(int nfiles, int ncolumnes) {
    setNfiles(nfiles);
    setNcolumnes(ncolumnes);
    ArrayList table = new ArrayList();
    for (int i = 0; i < nfiles; i++) {
      ArrayList fila = new ArrayList();
      for (int j = 0; j < ncolumnes; j++) {
        fila.add(null);
        }
      table.add(fila);
    }
    setTaula(table);
  }

  /*
   * This method adds a row to the table without indicating its values.
   */
  @SuppressWarnings("unchecked")
  public void afegeixFila() {
    int ncolumnes = getNcolumnes();
    ArrayList fila = new ArrayList();
    for (int j = 0; j < ncolumnes; j++) {
      fila.add(null);
    }
    getTaula().add(fila);
    setNfiles(getNfiles() + 1);
  }

  @SuppressWarnings("unchecked")
  public void afegeixFila(ArrayList llistaStrings) {
    getTaula().add(llistaStrings);
    setNfiles(getNfiles() + 1);
  }

  @SuppressWarnings("unchecked")
  public void setPosicio(int fila, int columna, String str) {
    ((ArrayList) getTaula().get(fila - 1)).set(columna - 1, str);
  }

  public String getPosicio(int fila, int columna) {
    return (String) ((ArrayList) getTaula().get(fila - 1)).get(columna - 1);
  }

  public void imprimeix() {
    System.out.println(this.getTaula());
  }
}
