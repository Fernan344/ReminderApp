using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReminderApp.Executor;

namespace ReminderApp
{
    class ReminderApp
    {
        static int Main(string[] args)
        {
            Executor.Executor.ExecuteCommand("START /B \"\" > \"%cd%\\jdk-18.0.2\bin\\java.exe\" > -jar \"ReminderApp.jar\"");
            return 0;
        }
    }
}
