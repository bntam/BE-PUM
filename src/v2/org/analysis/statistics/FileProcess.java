/**
 * Process some File functions, e.g. read file, append string, write file ...
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.*;

public class FileProcess {
	private String filePath;

	public FileProcess(String filePath) {
		this.filePath = filePath;
	}

	public boolean containFinalExtract(String data, FileProcess nJ, FileProcess nC, FileProcess eJ, FileProcess eC,
			FileProcess tJ, FileProcess tC) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String d[] = data.split(",");
			tC.appendFile(d[1]);
			nC.appendFile(d[2]);
			eC.appendFile(d[3]);
			String line = br.readLine();
			while (line != null) {
				// numFile++;
				// sb.append(line);
				// sb.append('\n');
				// if (!readInstFile(pathResult, line))
				// numUnprocessedFile++;
				String t[] = line.split(",");

				// if (line.contains(data)) {
				if (d[0].equals(t[0])) {
					tJ.appendFile(t[1]);
					nJ.appendFile(t[2]);
					eJ.appendFile(t[3]);
					br.close();
					result = true;

					if (Math.abs(Integer.parseInt(d[2]) - Integer.parseInt(t[2])) > 2
							|| Math.abs(Integer.parseInt(d[3]) - Integer.parseInt(t[3])) > 2)
						// System.out.println(d[0]);

						break;
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	public void readFile(StringArrayList n) {
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				n.insert(line);
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean contain(String data) {
		boolean result = false;
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				// numFile++;
				// sb.append(line);
				// sb.append('\n');
				// if (!readInstFile(pathResult, line))
				// numUnprocessedFile++;
				if (line.contains(data)) {
					br.close();
					result = true;
					break;
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	public boolean containFN(String data) {
		boolean result = false;
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				// numFile++;
				// sb.append(line);
				// sb.append('\n');
				// if (!readInstFile(pathResult, line))
				// numUnprocessedFile++;
				String t[] = line.split("_");

				// if (line.contains(data)) {
				if (data.startsWith(t[0])) {
					br.close();
					result = true;
					break;
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	public boolean appendFile(String data) {
		try {
			// String data = " This content will append to the end of the file";
			File file = new File(System.getProperty("user.dir") + "/" + this.filePath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getPath(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data + '\n');
			bufferWritter.close();

			// System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean appendInLine(String data) {
		try {
			// String data = " This content will append to the end of the file";
			File file = new File(System.getProperty("user.dir") + "/" + this.filePath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getPath(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data + " ");
			bufferWritter.close();

			// System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void listFileInDir(String path) {
		File folder = new File(path);
		for (File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()
			// && fileEntry.getName().endsWith("exe")
			) {
				// if (!check(fileEntry.getName())) {
				String t = fileEntry.getName();
				if (t.contains(".png") || t.contains("_asmcfg.dot") || t.contains("_cfa.dot") || t.contains("_jak.asm"))
					continue;
				this.appendFile(t);
				// if (numFile > 5)
				// break;
				// }
			}
		}
	}

	public boolean writeFile(String data) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(this.filePath, "UTF-8");
			writer.print(data);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getLastLine() {
		// TODO Auto-generated method stub
		String result = "";

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				// numFile++;
				// sb.append(line);
				// sb.append('\n');
				// if (!readInstFile(pathResult, line))
				// numUnprocessedFile++;
				// if (line.contains(data)) {
				// br.close();
				// result = true;
				// break;
				// }
				result = line;
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public void clearContentFile() {
		if (checkFileExisted()) {
			this.writeFile("");
		}
	}

	private boolean checkFileExisted() {
		try {
			// String data = " This content will append to the end of the file";
			File file = new File(this.filePath);
			// if file doesnt exists, then create it
			if (file.exists())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean containFinal(String data) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			// StringBuilder sb = new StringBuilder();
			String d[] = data.split(",");
			String line = br.readLine();
			while (line != null) {
				// numFile++;
				// sb.append(line);
				// sb.append('\n');
				// if (!readInstFile(pathResult, line))
				// numUnprocessedFile++;
				String t[] = line.split(",");

				// if (line.contains(data)) {
				if (d[0].equals(t[0])) {
					br.close();
					result = true;

					if (Math.abs(Integer.parseInt(d[2]) - Integer.parseInt(t[2])) > 2
							|| Math.abs(Integer.parseInt(d[3]) - Integer.parseInt(t[3])) > 2)
						System.out.println(d[0]);

					break;
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	public String getLineAt(int t) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.filePath));
			String line = br.readLine();
			while (line != null) {
				if (t==0)
					return line;
				line = br.readLine();
				t--;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "";
	}
}
