
using ToyLanguage.Model;

namespace ToyLanguage.Interfaces
{
    interface IMyStatement
    {
        /**
    * String representation
    *
    * @return String
    */
        string MyToString();

        ProgramState execute(ProgramState programState);

    }
}
