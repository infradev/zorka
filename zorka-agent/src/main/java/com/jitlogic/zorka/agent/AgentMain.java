/**
 * Copyright 2012-2013 Rafal Lewczuk <rafal.lewczuk@jitlogic.com>
 *
 * ZORKA is free software. You can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * ZORKA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * ZORKA. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jitlogic.zorka.agent;

import java.lang.instrument.Instrumentation;

/**
 * This class is responsible for bootstrapping zorka agent.
 *
 * @author rafal.lewczuk@jitlogic.com
 */
public class AgentMain {

    private static AgentInstance instance;

    /**
     * This is entry method of java agent.
     *
     * @param args arguments (supplied via -javaagent:/path/to/agent.jar=arguments)
     *
     * @param instr reference to JVM instrumentation interface
     */
    public static void premain(String args, Instrumentation instr) {

        //ZorkaConfig.loadProperties(args);

        instance = new AgentInstance(new ZorkaConfig(args));
        instance.start();

        if (instance.getConfig().boolCfg("spy", true)) {
            instr.addTransformer(instance.getClassTransformer());
        }

    }
}
