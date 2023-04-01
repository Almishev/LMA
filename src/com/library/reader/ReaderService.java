package com.library.reader;

import com.library.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ReaderService {

    private static final ReaderAccessor readerAccessor = new ReaderAccessor();
    private static final ReaderMapper readerMapper = new ReaderMapper();

    private static final String ID_NOT_FOUND_EXCEPTION="Item with ID %d was not found.";

    public List<Reader> getAllReaders() {
        List<String> readerStrings = readerAccessor.readAllReaders();
        List<Reader> readers = new ArrayList<>();
        for (String readerString : readerStrings) {
            Reader reader = readerMapper.mapStringToReader(readerString);
            readers.add(reader);
        }
        return readers;
    }

    public void addReader(String name) {
        int id = readerAccessor.readAllReaders().size() + 1;
        Reader reader = new Reader(id,name);
        String readerString = readerMapper.mapReaderToString(reader);
        readerAccessor.addNewReader(readerString);

    }

    public void editReader(int id, String name) throws ItemNotFoundException {


        List<Reader> readers = getAllReaders();
       Reader readerToEdit = getReaderByIDFromTheList(id, readers);
        if (readerToEdit == null) {
            return;
        }

        readerToEdit.setName(name);

        String readerString = readerMapper.mapReaderListToString(readers);
        readerAccessor.overWriteFile(readerString.toString());

    }

    public Reader getReaderByIDFromTheList(int id, List<Reader> readers) throws ItemNotFoundException {


        if (readers.size() <= id || id < 1) {

            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        Reader reader = null;
        for (Reader readerInTheList :readers) {
            if (readerInTheList.getReaderId() == id) {
                reader = readerInTheList;
                break;
            }
        }
        if (reader==null){
            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        return reader;

    }

    public void deleteReader (int id) throws ItemNotFoundException {
        List<Reader> readers = getAllReaders();
        Reader readerToRemove = getReaderByIDFromTheList(id, readers);
        readers.remove(readerToRemove);
        for (int i = readerToRemove.getReaderId()-1; i<readers.size();i++) {
            readers.get(i).setReaderId(i+1);
        }
        String readerString = readerMapper.mapReaderListToString(readers);
        readerAccessor.overWriteFile(readerString);

    }





}
