package github;

import java.util.ArrayList;
import java.util.List;

import utils.ReadTxt;

public class GithubManager {

	private List<String> links;

	public void start() {

		String gitUrlsPath = "repositories.txt";

		ReadTxt rt = new ReadTxt(gitUrlsPath);
		links = rt.readFile();

		//cloneProjects();
		//getCommitsLog();
		/*
		 * Run only after running all the commands of the console.
		 */
		checkoutProjects();

	}

	public void cloneProjects() {

		System.out.println("mkdir projects");
		System.out.println("cd projects");
		for (String gitLink : links) {
			if (gitLink.contains("git")) {
				System.out.println("git clone " + gitLink + " " + gitLink.substring(gitLink.lastIndexOf("/") + 1));
			}
		}
	}

	public void getCommitsLog() {

		// Get log from projects

		for (String gitLink : links) {
			if (gitLink.contains("git")) {
				System.out.println("cd " + gitLink.substring(gitLink.lastIndexOf("/") + 1));
				System.out.println("git log > log.txt");
				System.out.println("cd ..");
			}
		}
	}

	public List<String> collectCommits(List<String> log) {
		List<String> commits = new ArrayList<String>();

		for (String c : log) {
			if (c.startsWith("commit")) {
				if (c.contains("Author")) {
					commits.add(c.substring(c.indexOf("commit") + 7, c.indexOf("Author")));
				} else {
					commits.add(c.substring(c.indexOf("commit") + 7));
				}
			}
		}
		return commits;
	}

	public void checkoutProjects(){
		for (String gitLink : links) {
			if (gitLink.contains("git")) {
				ReadTxt readLog = new ReadTxt("projects/" + gitLink.substring(gitLink.lastIndexOf("/") + 1) + "/log.txt");
				List<String> log = readLog.readFile();
				List<String> commits = collectCommits(log);
				for (String commit : commits) {
					System.out.println("git clone " + gitLink + " " + gitLink.substring(gitLink.lastIndexOf("/") + 1)
							+ "_" + commit);
					System.out.println("cd " + gitLink.substring(gitLink.lastIndexOf("/") + 1) + "_" + commit);
					System.out.println("git checkout " + commit);
					System.out.println("cd ..");
				}
			}
		}
	}
}
