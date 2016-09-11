# MARSHAL

a simple marshaling and unmarshalling library for processing.

## Usage

### Supported data formats

Currently marshal supports the following formats:

 * json
 * xml
 * yaml

### Creating the Marshal Object

Pass `this` to the constructor of the marshal object like so:

```java
Marhsal marshal = new Marshal(this);
```

### Saving an Object To a File

Lets assume for these examples that we have a class called `Message` with one property: `content`.

```java
Message message = new Message();
message.setContent("hey");

//  a file called message.json will be created in the data folder
//  with the contents:
//      { "content": "hey" }
//
marshal.save(message, "message.json", DataFormat.JSON);
```

If we already have a `File` object we can use that instead:

```java
Message message = new Message();
message.setContent("hey");

File file = new File("message.json");

marshal.save(message, file, DataFormat.JSON);
```

### Loading an Object From a File

Let's assume we have a file called message.xml in the data folder.   
We can load it to an object like so:

```java
Message message = marshal.load("message.xml", Message.class, DataFormat.XML);
```

If you already have a `File` object we can use that instead:

```java
File file = new File("message.xml");
Message message = marshal.load(file, Message.class, DataFormat.XML);
```

### Marshaling an Object to A String

Rather than write an object to a file we can marshal it to a string to use later.

```java
Message message = new Message();
message.setContent("hey");

String output = marshal.marshal(message, DataFormat.JSON);

// prints { "content": "hey" }
println(output);
```

### Unmarshalling a String to an Object

If we have a a marshaled string we can unmarshall it back into an object:

```java
String json = "{ \"content\": \"hey\" }";
Message message = marshal.unmarshal(json, Message.class, DataFormat.JSON);

// prints hey
println(message.getContent());
```

### Transforming From One Format to Another

Marshal provides the `TransformationBuilder` which gives us some shorthand syntax for converting objects from one format to another.
For example:

```java
//message.xml contains "<Message><content>hey</content></Message>"
String json = marshal.transformFile("message.xml")
                            .ofType(Message.class)
                            .from(DataFormat.XML)
                            .to(DataFormat.JSON)
                            .getString();

// prints { "content": "hey" }
println(json);
```

We can even save the converted object directly to a file:

```java
// message.xml contains <Message><content>hey</content></Message>
marshal.transformFile("message.xml")
            .ofType(Message.class)
            .from(DataFormat.XML)
            .to(DataFormat.JSON)
            .andSaveTo("message.json");
// now there's a file in the data folder named message.json with the message in it.
```

## Building and Installing

### Building

To compile the library run:

```
./build.sh
```

### Installation

After running the build script we have `Marshal.zip` in the `target` folder.   
Extract the zip file in the libraries folder of your processing sketchbook.

## Contributing

If you want to add or fix something feel free to make an issue and pull request.
