package org.jenkinsci.plugins.ivytrigger;

import hudson.Util;
import hudson.console.AnnotatedLargeText;
import hudson.model.AbstractProject;
import hudson.model.Action;
import org.apache.commons.jelly.XMLOutput;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Gregory Boissinot
 */
public class IvyTriggerAction implements Action {

    private transient AbstractProject<?, ?> job;
    private transient File logFile;
    private transient String label;

    public IvyTriggerAction(AbstractProject<?, ?> job, File logFile, String label) {
        this.job = job;
        this.logFile = logFile;
        this.label = label;
    }

    @SuppressWarnings("unused")
    public AbstractProject<?, ?> getOwner() {
        return job;
    }

    @SuppressWarnings("unused")
    public String getLabel() {
        return label;
    }

    @SuppressWarnings("unused")
    public String getIconFileName() {
        return "clipboard.gif";
    }

    public String getDisplayName() {
        return "IvyTrigger Log";
    }

    @SuppressWarnings("unused")
    public String getUrlName() {
        return "ivyTriggerPollLog";
    }

    @SuppressWarnings("unused")
    public String getLog() throws IOException {
        return Util.loadFile(getLogFile());
    }

    public File getLogFile() {
        return logFile;
    }

    @SuppressWarnings("unused")
    public void writeLogTo(XMLOutput out) throws IOException {
        new AnnotatedLargeText<IvyTriggerAction>(getLogFile(), Charset.defaultCharset(), true, this).writeHtmlTo(0, out.asWriter());
    }

}