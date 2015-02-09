package asw1021;

import javax.servlet.*;
import java.io.*;

/**
 * Classe di utilità a supporto della creazione di listeners per eventi legati
 * alla gestione di risposte HTTP asincrone; in particolare si occupa di
 * implementare lasciando per default vuoti i corpi dei metodi necessari
 * all'interfaccia <code>AsyncListener</code> alla quale si riferisce.
 * 
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */


public class AsyncAdapter implements AsyncListener{
    @Override 
    public void onComplete(AsyncEvent event) throws IOException {}
    @Override
    public void onError(AsyncEvent event) throws IOException {}
    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {}
    @Override
    public void onTimeout(AsyncEvent event) throws IOException {}
}
