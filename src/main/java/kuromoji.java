import com.asterdata.ncluster.sqlmr.HelpInfo;
import com.asterdata.ncluster.sqlmr.OutputInfo;
import com.asterdata.ncluster.sqlmr.PartitionFunction;
import com.asterdata.ncluster.sqlmr.RowFunction;
import com.asterdata.ncluster.sqlmr.RuntimeContract;
import com.asterdata.ncluster.sqlmr.data.ColumnDefinition;
import com.asterdata.ncluster.sqlmr.data.PartitionDefinition;
import com.asterdata.ncluster.sqlmr.data.RowEmitter;
import com.asterdata.ncluster.sqlmr.data.RowIterator;
import com.asterdata.ncluster.sqlmr.data.SqlType;
import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.util.ArrayList;
import java.util.List;

@HelpInfo(
        usageSyntax = "kuromoji( on ... [ partition by ... ] )",
        shortDescription = "morphological analysis by kuromji.",
        longDescription = "",
        inputColumns = "*",
        outputColumns = "*",
        author = "ebyhr"
)
public final class kuromoji implements RowFunction, PartitionFunction
{
    public kuromoji(RuntimeContract contract)
    {
        ArrayList<ColumnDefinition> outputColumns = new ArrayList<>();
        outputColumns.add( new ColumnDefinition("position", SqlType.integer()) );
        outputColumns.add( new ColumnDefinition("surface", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("partofspeechlevel1", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("partofspeechlevel2", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("partofspeechlevel3", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("partofspeechlevel4", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("reading", SqlType.varchar()) );
        outputColumns.add( new ColumnDefinition("pronunciation", SqlType.varchar()) );
        contract.setOutputInfo( new OutputInfo(outputColumns) );
        contract.complete();
    }

    private static final Tokenizer tokenizer = new Tokenizer();

    public void operateOnSomeRows(RowIterator inputIterator, RowEmitter outputEmitter)
    {
        while ( inputIterator.advanceToNextRow() )
        {
            List<Token> tokens = tokenizer.tokenize(inputIterator.getStringAt(0));
            for (Token token : tokens) {
                outputEmitter.addInt(token.getPosition());
                outputEmitter.addString(token.getSurface());
                outputEmitter.addString(token.getPartOfSpeechLevel1());
                outputEmitter.addString(token.getPartOfSpeechLevel2());
                outputEmitter.addString(token.getPartOfSpeechLevel3());
                outputEmitter.addString(token.getPartOfSpeechLevel4());
                outputEmitter.addString(token.getReading());
                outputEmitter.addString(token.getPronunciation());
                outputEmitter.emitRow();
            }
        }

    }

    public void operateOnPartition(PartitionDefinition partition, RowIterator inputIterator, RowEmitter outputEmitter) { }
}
