package com.askimed.nf.test.lang;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.askimed.nf.test.lang.process.Process;
import com.askimed.nf.test.lang.workflow.Workflow;
import com.askimed.nf.test.util.FileUtil;

import groovy.lang.Closure;

public class TestContext {

	private Map<String, Object> params = new HashMap<String, Object>();

	private Workflow workflow = new Workflow();

	private Process process = new Process();

	public void setName(String name) {
		process.setName(name);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public void params(Closure closure) {
		closure.setDelegate(params);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
	}

	public void process(Closure<Object> closure) {
		closure.setDelegate(this);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
		Object mapping = closure.call();
		if (mapping != null) {
			process.setMapping(mapping.toString());
		}
	}

	public void clean(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				FileUtil.deleteDirectory(file);
			} else {
				file.delete();
			}
		}
	}

}