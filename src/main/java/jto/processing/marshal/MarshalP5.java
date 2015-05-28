package jto.processing.marshal;

import processing.core.PApplet;

import java.io.File;

/**
 * This is the main class of the MarshalP5 library and
 * the implemenation of the {@link} Marshal interface.
 */
public class MarshalP5 implements Marshal {

    private final PApplet parent;
    private final Marshallers marshallers;
    private final Unmarshallers unmarshallers;
    private final FileHandler fileHandler;

    public MarshalP5(PApplet parent) {
        this.parent = parent;
        marshallers = new Marshallers();
        unmarshallers = new Unmarshallers();
        fileHandler = new FileHandlerImpl();
    }

    private DataFormat toFormat(String filename) {
        String[] pieces = filename.split(".");
        if (pieces.length <= 1) {
            return null;
        }
        return DataFormat.forExtension(pieces[1]);
    }

    @Override
    public void save(Object obj, File file) {
        save(obj, file, toFormat(file.getName()));
    }

    @Override
    public void save(Object obj, String filename, DataFormat dataFormat) {
        fileHandler.saveFile(parent.dataPath(filename), marshal(obj, dataFormat));
    }

    @Override
    public void save(Object obj, File file, DataFormat dataFormat) {
        fileHandler.saveFile(file, marshal(obj, dataFormat));
    }

    @Override
    public Object load(File file) {
        return load(file, toFormat(file.getName()));
    }

    @Override
    public Object load(String filename, DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(filename), dataFormat);
    }

    @Override
    public <T> T load(String filename, Class<T> type) {
        return load(filename, type, toFormat(filename));
    }

    @Override
    public Object load(File file, DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(file), dataFormat);
    }

    @Override
    public <T> T load(String filename, Class<T> type, DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(filename), type, dataFormat);
    }

    @Override
    public Object unmarshal(String marshaledObject, DataFormat dataFormat) {
        return unmarshallers.forDataFormat(dataFormat).unmarshal(marshaledObject);
    }

    @Override
    public <T> T unmarshal(String marshaledObject, Class<T> type, DataFormat dataFormat) {
        return unmarshallers.forDataFormat(dataFormat).unmarshal(marshaledObject, type);
    }

    @Override
    public String marshal(Object obj, DataFormat dataFormat) {
        return marshallers.forDataFormat(dataFormat).marshal(obj);
    }

    @Override
    public TransformationBuilder transformFile(String filename) {
        return new TransformationBuilderImpl(parent.dataPath(filename), marshallers, unmarshallers, fileHandler);
    }

    @Override
    public TransformationBuilder transformFile(File file) {
        return new TransformationBuilderImpl(file, marshallers, unmarshallers, fileHandler);
    }

    @Override
    public TransformationBuilder transform(Object object) {
        return new TransformationBuilderImpl(object, marshallers, unmarshallers, fileHandler);
    }
}
