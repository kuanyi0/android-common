package com.yikuan.androidcommon.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author yikuan
 * @date 2019/10/14
 */
public class ShellUtils {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String CHARSET_NAME = "UTF-8";

    private ShellUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static CommandResult execCmd(String command, boolean isRoot) {
        return execCmd(new String[]{command}, isRoot);
    }

    public static CommandResult execCmd(List<String> commands, boolean isRoot) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot);
    }

    public static CommandResult execCmd(String[] commands, boolean isRoot) {
        return execCmd(commands, isRoot, true);
    }

    public static CommandResult execCmd(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, true, isNeedResultMsg);
    }

    public static CommandResult execCmd(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), true, isNeedResultMsg);
    }

    public static CommandResult execCmd(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }
        Process process = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;

        try {
            process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                os.write(command.getBytes());
                os.writeBytes(LINE_SEPARATOR);
                os.flush();
            }
            os.writeBytes("exit" + LINE_SEPARATOR);
            os.flush();
            result = process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successReader = new BufferedReader(new InputStreamReader(process.getInputStream(), CHARSET_NAME));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), CHARSET_NAME));
                String line;
                if ((line = successReader.readLine()) != null) {
                    successMsg.append(line);
                    while ((line = successReader.readLine()) != null) {
                        successMsg.append(LINE_SEPARATOR).append(line);
                    }
                }
                if ((line = errorReader.readLine()) != null) {
                    errorMsg.append(line);
                    while ((line = errorReader.readLine()) != null) {
                        errorMsg.append(LINE_SEPARATOR).append(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(os, successReader, errorReader);
            if (process != null) {
                process.destroy();
            }
        }

        return new CommandResult(
                result,
                successMsg == null ? null : successMsg.toString(),
                errorMsg == null ? null : errorMsg.toString());
    }

    public static class CommandResult {
        int result;
        String successMsg;
        String errorMsg;

        CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
