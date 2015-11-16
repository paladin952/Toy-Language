using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Interfaces;
using ToyLanguage.UiPackage;

namespace ToyLanguage
{
    class Program
    {
        static void Main(string[] args)
        {
            //init objects
            IRepository repository = new Repository();
            Controller controller = new Controller(repository);
            UserInterface ui = new UserInterface(controller);

            //start ui
            ui.run();
        }
    }
}
