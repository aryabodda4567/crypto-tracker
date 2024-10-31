package org.project.sih.core.processes;

public interface Processes {
    public void start();
    public void stop();
    public void init();
    public void process();
    public void visit(String WalletAddress);
}
