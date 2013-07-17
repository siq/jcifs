/* Test the following file operations:
 * 
 * canRead
 *     false - a target that is already open by another process
 *     false - the target does not exist
 *     true  - the file exists and there are no sharing issues
 * canWrite
 *     true  - the file exists and there are no sharing issues
 *     false - the file is marked read-only
 *     false - the file does not exist
 * delete
 *     true  - the file existed and was succcessfully deleted
 *     false - the target did not exist
 *     false - the target is a share, server, workgroup or similar
 *     false - the target or file under the target directory failed was read-only
 * exists
 *     true  - the target, share, IPC share, named pipe, server, or workgroup exists
 *     false - the opposite of the above
 * isDirectory
 *     true  - the target is a workgroup, server, share, or directory
 *     false - the target is not one of the above or does not exist
 * isFile
 *     direct opposite of isDirectory
 * isHidden
 *     true  - target is share ending in $ or marked as hidden
 * length
 *     the file was created to be no larger than ~2G and reports back the size specified
 * mkdir
 *     true  - a directory was created successfuly
 *     false - the directory could not be created
 * renameTo
 *     true  - the target was renamed
 */

import jcifs.smb.*;
import java.io.IOException;
import java.util.Date;

public class OutOfSpace 
{

    public static void main( String argv[] ) throws Exception 
    {

        if( argv.length != 1 ) {
            System.out.println( "Must provide an SMB URL of a remote location on which tests will be conducted." );
            System.exit( 1 );
        }
        String destDir = argv[0];
        String fileName = destDir + "OutOfSpace.txt";
        int len = 1024 * 1024;
        byte[] b = new byte[len];
        for (int i = 0; i < len; ++i) b[i] = 'a';

        // Create a really big file

        SmbFile f = null;
        try 
            {
                f = new SmbFile( fileName );
                SmbFileOutputStream o = new SmbFileOutputStream( f );
                
                while (true) // write until it dies a horrible death
                    {
                        o.write(b);
                        System.out.print('.');
                        System.out.flush();
                    }
                //o.close();
            }
        catch( IOException ioe ) 
            {
                System.out.printf("Ex: message: %s\n\t%s\n", 
                                  ioe.getMessage(), ioe.toString());
                ioe.printStackTrace();
            }

    } // main()

} // class OutOfSpace
