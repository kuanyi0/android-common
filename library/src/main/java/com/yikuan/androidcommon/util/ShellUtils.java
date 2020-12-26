package com.yikuan.androidcommon.util;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * @author yikuan
 * @date 2019/10/14
 */
public class ShellUtils {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String CHARSET_NAME = "UTF-8";
    private static final String COMMAND_SH = "sh";
    private static final String COMMAND_EXIT = "exit" + LINE_SEPARATOR;

    private ShellUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
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
        if (isRoot) {
            return execCmdForRoot(commands, isNeedResultMsg);
        } else {
            return execCmdForUser(commands, isNeedResultMsg);
        }
    }

    private static CommandResult execCmdForUser(String[] commands, boolean isNeedResultMsg) {
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
            process = Runtime.getRuntime().exec(COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                os.write(command.getBytes());
                os.writeBytes(LINE_SEPARATOR);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
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

    private static CommandResult execCmdForRoot(String[] commands, boolean isNeedResultMsg) {
        StringBuilder cmdBuilder = new StringBuilder();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == null) {
                continue;
            }
            cmdBuilder.append(commands[i]);
            if (i != (commands.length - 1)) {
                cmdBuilder.append(" && ");
            }
        }
        String command = cmdBuilder.toString();
        LocalSocket socket = null;
        InputStream in = null;
        OutputStream out = null;
        String line = "";
        StringBuilder sb = new StringBuilder(line);

        try {
            socket = new LocalSocket();
            LocalSocketAddress address = new LocalSocketAddress("rootdaemon", LocalSocketAddress.Namespace.RESERVED);
            socket.connect(address);
            out = socket.getOutputStream();
            out.write(command.getBytes());
            in = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(in, out, socket);
        }

        return getCommandResultFromBuilder(sb, isNeedResultMsg);
    }

    private static CommandResult getCommandResultFromBuilder(StringBuilder buffer, boolean isNeedResultMsg) {
        String successMsg = null;
        String errorMsg = null;
        int result = getResultFromBuilder(buffer);
        if (result == 0) {
            successMsg = isNeedResultMsg ? getMessageFromBuilder(buffer) : null;
        } else {
            errorMsg = isNeedResultMsg ? getMessageFromBuilder(buffer) : null;
        }
        return new ShellUtils.CommandResult(result, successMsg, errorMsg);
    }

    private static int getResultFromBuilder(StringBuilder buffer) {
        String[] strings = buffer.toString().split("\n");
        if (strings.length > 0) {
            String[] results = strings[strings.length - 1].split(" ");
            if (results.length == 3) {
                return Integer.parseInt(results[1]);
            }
        }
        return -1;
    }

    private static String getMessageFromBuilder(StringBuilder buffer) {
        String[] strings = buffer.toString().split("\n");
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            msg.append(strings[i]).append('\n');
        }
        return msg.toString();
    }

    public static class CommandResult {
        public int result;
        public String successMsg;
        public String errorMsg;

        CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
